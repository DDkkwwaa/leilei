<template>
  <div class="login-page">
    <div class="login-layout">
      <section class="login-visual">
        <div class="visual-brand">
          <div class="visual-brand__icon">
            <img src="../assets/logo.png" alt="logo">
          </div>
          <span>Purchase System</span>
        </div>

        <div class="visual-characters">
          <div ref="stage" class="characters-stage">
            <div
              ref="purple"
              class="character purple"
              :style="purpleStyle"
            >
              <div class="character-eyes" :style="purpleEyesStyle">
                <div class="eye-ball" :style="eyeBallStyle(18, isPurpleBlinking)">
                  <div class="eye-pupil" :style="pupilStyle(7, purpleEyeOffset)"></div>
                </div>
                <div class="eye-ball" :style="eyeBallStyle(18, isPurpleBlinking)">
                  <div class="eye-pupil" :style="pupilStyle(7, purpleEyeOffset)"></div>
                </div>
              </div>
            </div>

            <div
              ref="black"
              class="character black"
              :style="blackStyle"
            >
              <div class="character-eyes" :style="blackEyesStyle">
                <div class="eye-ball" :style="eyeBallStyle(16, isBlackBlinking)">
                  <div class="eye-pupil" :style="pupilStyle(6, blackEyeOffset)"></div>
                </div>
                <div class="eye-ball" :style="eyeBallStyle(16, isBlackBlinking)">
                  <div class="eye-pupil" :style="pupilStyle(6, blackEyeOffset)"></div>
                </div>
              </div>
            </div>

            <div
              ref="orange"
              class="character orange"
              :style="orangeStyle"
            >
              <div class="character-pupils" :style="orangeEyesStyle">
                <div class="solo-pupil" :style="soloPupilStyle(12, orangeEyeOffset)"></div>
                <div class="solo-pupil" :style="soloPupilStyle(12, orangeEyeOffset)"></div>
              </div>
            </div>

            <div
              ref="yellow"
              class="character yellow"
              :style="yellowStyle"
            >
              <div class="character-pupils" :style="yellowEyesStyle">
                <div class="solo-pupil" :style="soloPupilStyle(12, yellowEyeOffset)"></div>
                <div class="solo-pupil" :style="soloPupilStyle(12, yellowEyeOffset)"></div>
              </div>
              <div class="yellow-mouth" :style="yellowMouthStyle"></div>
            </div>
          </div>
        </div>

        <div class="visual-footer">
          <span>Interactive login experience</span>
          <span>Typing / blinking / peeking</span>
        </div>
      </section>

      <section class="login-panel">
        <div class="login-panel__header">
          <div class="mobile-brand">
            <img src="../assets/logo.png" alt="logo">
            <span>代码管理</span>
          </div>
          <h1>欢迎回来</h1>
          <p>请输入账号和密码登录系统</p>
        </div>

        <el-form ref="formName" :model="form" :rules="rules" class="login-form" @submit.native.prevent>
          <el-form-item prop="username">
            <div class="field-label">账号</div>
            <el-input
              v-model="form.username"
              placeholder="admin"
              @focus="setTypingState(true)"
              @blur="setTypingState(false)"
              @input="handleUsernameInput"
            ></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <div class="field-label">密码</div>
            <div class="password-wrap">
              <el-input
                v-model="form.password"
                :type="showPassword ? 'text' : 'password'"
                placeholder="请输入密码"
                @focus="setTypingState(true)"
                @blur="setTypingState(false)"
                @input="handlePasswordInput"
                @keyup.enter.native="submitFormData"
              ></el-input>
              <button type="button" class="password-toggle" @click="togglePassword">
                {{ showPassword ? '隐藏' : '显示' }}
              </button>
            </div>
          </el-form-item>

          <div v-if="error" class="login-error">{{ error }}</div>

          <div class="login-actions">
            <el-button type="primary" :loading="loading" @click="submitFormData">登录</el-button>
            <button type="button" class="ghost-link">忘记密码</button>
          </div>
        </el-form>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loading: false,
      showPassword: false,
      error: "",
      mouseX: 0,
      mouseY: 0,
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      isPurpleBlinking: false,
      isBlackBlinking: false,
      isTyping: false,
      isLookingAtEachOther: false,
      isPurplePeeking: false,
      purpleBlinkTimer: null,
      blackBlinkTimer: null,
      typingLookTimer: null,
      peekTimeout: null,
      peekCycleTimer: null,
    };
  },
  computed: {
    purpleMetrics() {
      return this.calculatePosition("purple");
    },
    blackMetrics() {
      return this.calculatePosition("black");
    },
    yellowMetrics() {
      return this.calculatePosition("yellow");
    },
    orangeMetrics() {
      return this.calculatePosition("orange");
    },
    purpleStyle() {
      let translateX = this.purpleMetrics.swayX || 0;
      let translateY = this.purpleMetrics.swayY || 0;
      let rotate = this.purpleMetrics.rotate || 0;
      let skew = this.purpleMetrics.bodySkew || 0;

      if (this.form.password && this.showPassword) {
        skew = this.purpleMetrics.bodySkew * 0.35;
        translateX += 4;
      }

      return {
        left: "70px",
        width: "180px",
        height: "400px",
        transform: `translate(${translateX}px, ${translateY}px) rotate(${rotate}deg) skewX(${skew}deg)`,
      };
    },
    blackStyle() {
      let translateX = this.blackMetrics.swayX || 0;
      let translateY = this.blackMetrics.swayY || 0;
      let rotate = this.blackMetrics.rotate || 0;
      let skew = this.blackMetrics.bodySkew || 0;

      if (this.form.password && this.showPassword) {
        skew = this.blackMetrics.bodySkew * 0.35;
      } else if (this.isLookingAtEachOther) {
        translateX += 4;
      }

      return {
        left: "240px",
        width: "120px",
        height: "310px",
        transform: `translate(${translateX}px, ${translateY}px) rotate(${rotate}deg) skewX(${skew}deg)`,
      };
    },
    orangeStyle() {
      const skew = this.form.password && this.showPassword ? 0 : this.orangeMetrics.bodySkew || 0;
      return {
        left: "0px",
        width: "240px",
        height: "200px",
        transform: `translate(${this.orangeMetrics.swayX || 0}px, ${this.orangeMetrics.swayY || 0}px) rotate(${this.orangeMetrics.rotate || 0}deg) skewX(${skew}deg)`,
      };
    },
    yellowStyle() {
      const skew = this.form.password && this.showPassword ? 0 : this.yellowMetrics.bodySkew || 0;
      return {
        left: "310px",
        width: "140px",
        height: "230px",
        transform: `translate(${this.yellowMetrics.swayX || 0}px, ${this.yellowMetrics.swayY || 0}px) rotate(${this.yellowMetrics.rotate || 0}deg) skewX(${skew}deg)`,
      };
    },
    purpleEyesStyle() {
      if (this.form.password && this.showPassword) {
        return { left: "20px", top: "35px" };
      }
      if (this.isLookingAtEachOther) {
        return { left: "55px", top: "65px" };
      }
      return {
        left: `${45 + this.purpleMetrics.faceX}px`,
        top: `${40 + this.purpleMetrics.faceY}px`,
      };
    },
    blackEyesStyle() {
      if (this.form.password && this.showPassword) {
        return { left: "10px", top: "28px" };
      }
      if (this.isLookingAtEachOther) {
        return { left: "32px", top: "12px" };
      }
      return {
        left: `${26 + this.blackMetrics.faceX}px`,
        top: `${32 + this.blackMetrics.faceY}px`,
      };
    },
    orangeEyesStyle() {
      if (this.form.password && this.showPassword) {
        return { left: "50px", top: "85px" };
      }
      return {
        left: `${82 + (this.orangeMetrics.faceX || 0)}px`,
        top: `${90 + (this.orangeMetrics.faceY || 0)}px`,
      };
    },
    yellowEyesStyle() {
      if (this.form.password && this.showPassword) {
        return { left: "20px", top: "35px" };
      }
      return {
        left: `${52 + (this.yellowMetrics.faceX || 0)}px`,
        top: `${40 + (this.yellowMetrics.faceY || 0)}px`,
      };
    },
    yellowMouthStyle() {
      if (this.form.password && this.showPassword) {
        return {
          left: `${2 + this.yellowEyeOffset.x * 0.6}px`,
          top: `${88 + this.yellowEyeOffset.y * 0.5}px`,
          transform: `rotate(${this.yellowMetrics.rotate || 0}deg)`,
        };
      }
      return {
        left: `${30 + (this.yellowMetrics.faceX || 0) * 1.25 + this.yellowEyeOffset.x * 0.9}px`,
        top: `${88 + (this.yellowMetrics.faceY || 0) * 1.18 + this.yellowEyeOffset.y * 0.7}px`,
        transform: `rotate(${(this.yellowMetrics.rotate || 0) * 0.9}deg)`,
      };
    },
    purpleEyeOffset() {
      if (this.form.password && this.showPassword) {
        return this.isPurplePeeking ? { x: 4, y: 5 } : { x: -4, y: -4 };
      }
      if (this.isLookingAtEachOther) {
        return { x: 3, y: 4 };
      }
      return this.limitPupilOffset("purple", 9);
    },
    blackEyeOffset() {
      if (this.form.password && this.showPassword) {
        return { x: -4, y: -4 };
      }
      if (this.isLookingAtEachOther) {
        return { x: 0, y: -4 };
      }
      return this.limitPupilOffset("black", 8);
    },
    orangeEyeOffset() {
      if (this.form.password && this.showPassword) {
        return { x: -5, y: -4 };
      }
      return this.limitPupilOffset("orange", 10);
    },
    yellowEyeOffset() {
      if (this.form.password && this.showPassword) {
        return { x: -5, y: -4 };
      }
      return this.limitPupilOffset("yellow", 10);
    },
  },
  mounted() {
    window.addEventListener("mousemove", this.handleMouseMove);
    this.$nextTick(() => {
      const stage = this.$refs.stage;
      if (!stage) {
        return;
      }
      const rect = stage.getBoundingClientRect();
      this.mouseX = rect.left + rect.width / 2;
      this.mouseY = rect.top + rect.height / 2;
    });
    this.schedulePurpleBlink();
    this.scheduleBlackBlink();
    this.syncPeekLoop();
  },
  beforeDestroy() {
    window.removeEventListener("mousemove", this.handleMouseMove);
    clearTimeout(this.purpleBlinkTimer);
    clearTimeout(this.blackBlinkTimer);
    clearTimeout(this.typingLookTimer);
    clearTimeout(this.peekTimeout);
    clearTimeout(this.peekCycleTimer);
  },
  methods: {
    handleMouseMove(e) {
      this.mouseX = e.clientX;
      this.mouseY = e.clientY;
    },
    calculatePosition(refName) {
      const element = this.$refs[refName];
      if (!element) {
        return { faceX: 0, faceY: 0, bodySkew: 0 };
      }

      const rect = element.getBoundingClientRect();
      const stage = this.$refs.stage;
      const stageRect = stage ? stage.getBoundingClientRect() : rect;
      const stageCenterX = stageRect.left + stageRect.width / 2;
      const stageCenterY = stageRect.top + stageRect.height / 2;
      const centerX = rect.left + rect.width / 2;
      const centerY = rect.top + rect.height / 3;
      const deltaX = this.mouseX - centerX;
      const deltaY = this.mouseY - centerY;
      const stageDeltaX = this.mouseX - stageCenterX;
      const stageDeltaY = this.mouseY - stageCenterY;
      const depthMap = {
        purple: 1.35,
        black: 1.1,
        orange: 0.9,
        yellow: 1.0,
      };
      const depth = depthMap[refName] || 1;

      return {
        faceX: Math.max(-24, Math.min(24, deltaX / 11)),
        faceY: Math.max(-16, Math.min(16, deltaY / 16)),
        bodySkew: Math.max(-14, Math.min(14, (-stageDeltaX / 30) * depth)),
        swayX: Math.max(-34, Math.min(34, (stageDeltaX / 16) * depth)),
        swayY: Math.max(-18, Math.min(18, (stageDeltaY / 24) * depth)),
        rotate: Math.max(-12, Math.min(12, (stageDeltaX / 42) * depth)),
      };
    },
    limitPupilOffset(refName, maxDistance) {
      const element = this.$refs[refName];
      if (!element) {
        return { x: 0, y: 0 };
      }

      const rect = element.getBoundingClientRect();
      const centerX = rect.left + rect.width / 2;
      const centerY = rect.top + rect.height / 2;
      const deltaX = this.mouseX - centerX;
      const deltaY = this.mouseY - centerY;
      const distance = Math.min(Math.sqrt(deltaX * deltaX + deltaY * deltaY), maxDistance);
      const angle = Math.atan2(deltaY, deltaX);

      return {
        x: Math.cos(angle) * distance,
        y: Math.sin(angle) * distance,
      };
    },
    eyeBallStyle(size, blinking) {
      return {
        width: `${size}px`,
        height: blinking ? "2px" : `${size}px`,
      };
    },
    pupilStyle(size, offset) {
      return {
        width: `${size}px`,
        height: `${size}px`,
        transform: `translate(${offset.x}px, ${offset.y}px)`,
      };
    },
    soloPupilStyle(size, offset) {
      return {
        width: `${size}px`,
        height: `${size}px`,
        transform: `translate(${offset.x}px, ${offset.y}px)`,
      };
    },
    schedulePurpleBlink() {
      const delay = Math.random() * 4000 + 3000;
      this.purpleBlinkTimer = setTimeout(() => {
        this.isPurpleBlinking = true;
        setTimeout(() => {
          this.isPurpleBlinking = false;
          this.schedulePurpleBlink();
        }, 150);
      }, delay);
    },
    scheduleBlackBlink() {
      const delay = Math.random() * 4000 + 3000;
      this.blackBlinkTimer = setTimeout(() => {
        this.isBlackBlinking = true;
        setTimeout(() => {
          this.isBlackBlinking = false;
          this.scheduleBlackBlink();
        }, 150);
      }, delay);
    },
    setTypingState(state) {
      this.isTyping = state;
      if (state) {
        this.isLookingAtEachOther = true;
        clearTimeout(this.typingLookTimer);
        this.typingLookTimer = setTimeout(() => {
          this.isLookingAtEachOther = false;
        }, 800);
      } else {
        this.isLookingAtEachOther = false;
      }
    },
    handleUsernameInput() {
      this.error = "";
      this.setTypingState(true);
    },
    handlePasswordInput() {
      this.error = "";
      this.setTypingState(true);
      this.syncPeekLoop();
    },
    togglePassword() {
      this.showPassword = !this.showPassword;
      this.syncPeekLoop();
    },
    syncPeekLoop() {
      clearTimeout(this.peekTimeout);
      clearTimeout(this.peekCycleTimer);
      this.isPurplePeeking = false;

      if (!(this.form.password && this.showPassword)) {
        return;
      }

      const schedule = () => {
        this.peekCycleTimer = setTimeout(() => {
          this.isPurplePeeking = true;
          this.peekTimeout = setTimeout(() => {
            this.isPurplePeeking = false;
            schedule();
          }, 800);
        }, Math.random() * 3000 + 2000);
      };

      schedule();
    },
    submitFormData() {
      this.$refs.formName.validate((valid) => {
        if (!valid || this.loading) {
          return false;
        }

        this.error = "";
        this.loading = true;
        this.$http
          .post("/login", this.form)
          .then((res) => {
            if (res.data.code === 200) {
              sessionStorage.setItem("token", res.headers.authorization);
              sessionStorage.setItem("username", this.form.username);
              this.$router.push("/");
              return;
            }
            this.error = `${res.data.msg || "登录失败"}，或该员工未分配角色`;
          })
          .catch(() => {
            this.error = "登录请求失败，请检查服务是否正常";
          })
          .finally(() => {
            this.loading = false;
          });
      });
    },
  },
};
</script>

<style lang="less" scoped>
.login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 24px;
  background:
    radial-gradient(circle at 18% 20%, rgba(138, 167, 255, 0.18), transparent 24%),
    radial-gradient(circle at 82% 16%, rgba(255, 255, 255, 0.72), transparent 18%),
    linear-gradient(180deg, #f4f7fd 0%, #eef3fb 100%);
}

.login-layout {
  position: relative;
  display: grid;
  min-height: clamp(400px, 52vh, 520px);
  grid-template-columns: 0.95fr 1.05fr;
  width: min(1180px, calc(100vw - 48px));
  border-radius: 28px;
  overflow: hidden;
  border: 1px solid rgba(255, 255, 255, 0.72);
  background: linear-gradient(135deg, rgba(255, 255, 255, 0.82), rgba(246, 249, 255, 0.72));
  backdrop-filter: blur(20px) saturate(135%);
  box-shadow:
    0 28px 80px rgba(62, 83, 138, 0.16),
    0 14px 34px rgba(117, 137, 188, 0.14),
    inset 0 1px 0 rgba(255, 255, 255, 0.88);

  &::before {
    content: "";
    position: absolute;
    inset: 10px;
    border-radius: 22px;
    border: 1px solid rgba(255, 255, 255, 0.22);
    pointer-events: none;
  }
}

.login-visual {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 28px 34px 22px;
  overflow: hidden;
  color: #fff;
  background: linear-gradient(135deg, #5b6df6 0%, #5262db 45%, #4654c3 100%);
  box-shadow: inset -1px 0 0 rgba(255, 255, 255, 0.16);

  &::before {
    content: "";
    position: absolute;
    inset: 0;
    background: radial-gradient(circle at 78% 18%, rgba(255, 255, 255, 0.16), transparent 20%);
  }

  &::after {
    content: "";
    position: absolute;
    inset: 14px;
    border-radius: 22px;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0.08), transparent 28%);
    border: 1px solid rgba(255, 255, 255, 0.12);
    pointer-events: none;
  }
}

.visual-brand {
  position: relative;
  z-index: 1;
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 18px;
  font-weight: 700;
  text-shadow: 0 6px 18px rgba(28, 45, 111, 0.28);

  &__icon {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 42px;
    height: 42px;
    border-radius: 14px;
    border: 1px solid rgba(255, 255, 255, 0.18);
    background: rgba(255, 255, 255, 0.18);
    backdrop-filter: blur(10px);
    box-shadow:
      inset 0 1px 0 rgba(255, 255, 255, 0.22),
      0 10px 18px rgba(32, 49, 112, 0.2);

    img {
      width: 26px;
      height: 26px;
    }
  }
}

.visual-characters {
  position: relative;
  z-index: 1;
  display: flex;
  align-items: end;
  justify-content: center;
  height: 230px;
}

.characters-stage {
  position: relative;
  width: 550px;
  height: 400px;
  transform: scale(0.58);
  transform-origin: center bottom;
}

.character {
  position: absolute;
  bottom: 0;
  transform-origin: bottom center;
  transition: all 0.1s linear;
}

.character-eyes,
.character-pupils,
.yellow-mouth {
  position: absolute;
  transition: all 0.08s linear;
}

.character-eyes {
  display: flex;
  gap: 8px;
}

.character-pupils {
  display: flex;
  align-items: center;
  gap: 24px;
}

.eye-ball {
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 999px;
  background: #fff;
  transition: all 0.08s linear;
}

.eye-pupil,
.solo-pupil {
  border-radius: 999px;
  background: #2d2d2d;
  transition: transform 0.06s linear;
}

.purple {
  z-index: 1;
  border-radius: 10px 10px 0 0;
  background: #6c3ff5;
}

.black {
  z-index: 2;
  border-radius: 8px 8px 0 0;
  background: #2d2d2d;
}

.orange {
  z-index: 3;
  border-radius: 120px 120px 0 0;
  background: #ff9b6b;
}

.yellow {
  z-index: 4;
  border-radius: 70px 70px 0 0;
  background: #e8d754;
}

.yellow-mouth {
  width: 38px;
  height: 4px;
  border-radius: 999px;
  background: #2d2d2d;
}

.visual-footer {
  position: relative;
  z-index: 1;
  display: flex;
  gap: 20px;
  color: rgba(255, 255, 255, 0.74);
  font-size: 12px;
  text-shadow: 0 4px 14px rgba(28, 45, 111, 0.22);
}

.login-panel {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 34px 38px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.74), rgba(246, 249, 255, 0.86));
  border-left: 1px solid rgba(255, 255, 255, 0.55);
  backdrop-filter: blur(14px);

  &::before {
    content: "";
    position: absolute;
    inset: 16px 16px 16px 8px;
    border-radius: 24px;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0.28), rgba(255, 255, 255, 0.08));
    border: 1px solid rgba(255, 255, 255, 0.42);
    box-shadow:
      inset 0 1px 0 rgba(255, 255, 255, 0.7),
      inset 0 -1px 0 rgba(205, 216, 244, 0.2);
    pointer-events: none;
  }
}

.login-panel__header {
  position: relative;
  z-index: 1;
  margin-bottom: 20px;

  h1 {
    margin: 10px 0 6px;
    color: #101828;
    font-size: 26px;
    line-height: 1.1;
    font-weight: 700;
  }

  p {
    color: #667085;
    font-size: 14px;
  }
}

.mobile-brand {
  display: none;
  align-items: center;
  gap: 10px;
  color: #101828;
  font-size: 18px;
  font-weight: 700;

  img {
    width: 32px;
    height: 32px;
  }
}

.login-form {
  position: relative;
  z-index: 1;
  max-width: 420px;

  /deep/ .el-form-item {
    margin-bottom: 14px;
  }

  /deep/ .el-input__inner {
    height: 48px;
    border: 1px solid rgba(255, 255, 255, 0.76);
    border-radius: 14px;
    background: linear-gradient(180deg, rgba(248, 251, 255, 0.94), rgba(232, 239, 250, 0.88));
    box-shadow:
      inset 1px 1px 0 rgba(255, 255, 255, 0.9),
      inset -1px -1px 0 rgba(205, 215, 235, 0.45),
      8px 8px 18px rgba(173, 185, 213, 0.16),
      -6px -6px 16px rgba(255, 255, 255, 0.7);
    color: #0f172a;
  }

  /deep/ .el-input__inner:focus {
    border-color: #5b6df6;
    background: rgba(255, 255, 255, 0.96);
    box-shadow:
      inset 1px 1px 0 rgba(255, 255, 255, 0.92),
      inset -1px -1px 0 rgba(204, 214, 236, 0.38),
      0 0 0 4px rgba(91, 109, 246, 0.12),
      10px 12px 24px rgba(173, 185, 213, 0.22);
  }
}

.field-label {
  margin-bottom: 8px;
  color: #344054;
  font-size: 14px;
  font-weight: 600;
}

.password-wrap {
  position: relative;
}

.password-toggle {
  position: absolute;
  top: 50%;
  right: 14px;
  z-index: 2;
  border: none;
  padding: 6px 10px;
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.52);
  box-shadow:
    inset 0 1px 0 rgba(255, 255, 255, 0.8),
    0 4px 10px rgba(173, 185, 213, 0.2);
  color: #5b6df6;
  font-size: 13px;
  cursor: pointer;
  transform: translateY(-50%);
}

.login-error {
  margin-bottom: 18px;
  padding: 12px 14px;
  border: 1px solid #fecdca;
  border-radius: 12px;
  color: #b42318;
  background: #fef3f2;
  font-size: 13px;
}

.login-actions {
  display: flex;
  align-items: center;
  gap: 14px;

  /deep/ .el-button {
    width: 100%;
    max-width: 180px;
    height: 48px;
    border: none;
    border-radius: 14px;
    background: linear-gradient(135deg, #6878ff 0%, #5567f1 100%);
    box-shadow:
      0 16px 28px rgba(91, 109, 246, 0.28),
      inset 0 1px 0 rgba(255, 255, 255, 0.34);
    font-size: 15px;
  }
}

.ghost-link {
  border: none;
  padding: 10px 14px;
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.52);
  box-shadow:
    inset 0 1px 0 rgba(255, 255, 255, 0.82),
    0 8px 18px rgba(173, 185, 213, 0.18);
  color: #5b6df6;
  font-size: 14px;
  cursor: pointer;
}

@media (max-width: 1080px) {
  .login-layout {
    grid-template-columns: 1fr;
    width: min(680px, calc(100vw - 24px));
    min-height: auto;
    border-radius: 24px;
  }

  .login-visual {
    min-height: 320px;
    padding: 28px 22px 0;
  }

  .visual-characters {
    height: 200px;
  }

  .characters-stage {
    transform: scale(0.52);
  }

  .login-panel {
    padding: 32px 22px 40px;
    border-left: none;
    border-top: 1px solid rgba(214, 223, 238, 0.7);
  }

  .mobile-brand {
    display: flex;
  }

  .login-panel__header h1 {
    font-size: 28px;
  }
}
</style>
