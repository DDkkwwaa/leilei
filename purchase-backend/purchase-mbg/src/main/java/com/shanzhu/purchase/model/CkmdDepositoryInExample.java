package com.shanzhu.purchase.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CkmdDepositoryInExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CkmdDepositoryInExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSourceNumberIsNull() {
            addCriterion("source_number is null");
            return (Criteria) this;
        }

        public Criteria andSourceNumberIsNotNull() {
            addCriterion("source_number is not null");
            return (Criteria) this;
        }

        public Criteria andSourceNumberEqualTo(Integer value) {
            addCriterion("source_number =", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberNotEqualTo(Integer value) {
            addCriterion("source_number <>", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberGreaterThan(Integer value) {
            addCriterion("source_number >", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_number >=", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberLessThan(Integer value) {
            addCriterion("source_number <", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberLessThanOrEqualTo(Integer value) {
            addCriterion("source_number <=", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberIn(List<Integer> values) {
            addCriterion("source_number in", values, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberNotIn(List<Integer> values) {
            addCriterion("source_number not in", values, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberBetween(Integer value1, Integer value2) {
            addCriterion("source_number between", value1, value2, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("source_number not between", value1, value2, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andInIdIsNull() {
            addCriterion("in_id is null");
            return (Criteria) this;
        }

        public Criteria andInIdIsNotNull() {
            addCriterion("in_id is not null");
            return (Criteria) this;
        }

        public Criteria andInIdEqualTo(Long value) {
            addCriterion("in_id =", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotEqualTo(Long value) {
            addCriterion("in_id <>", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdGreaterThan(Long value) {
            addCriterion("in_id >", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdGreaterThanOrEqualTo(Long value) {
            addCriterion("in_id >=", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdLessThan(Long value) {
            addCriterion("in_id <", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdLessThanOrEqualTo(Long value) {
            addCriterion("in_id <=", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdIn(List<Long> values) {
            addCriterion("in_id in", values, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotIn(List<Long> values) {
            addCriterion("in_id not in", values, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdBetween(Long value1, Long value2) {
            addCriterion("in_id between", value1, value2, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotBetween(Long value1, Long value2) {
            addCriterion("in_id not between", value1, value2, "inId");
            return (Criteria) this;
        }

        public Criteria andDepositoryIsNull() {
            addCriterion("depository is null");
            return (Criteria) this;
        }

        public Criteria andDepositoryIsNotNull() {
            addCriterion("depository is not null");
            return (Criteria) this;
        }

        public Criteria andDepositoryEqualTo(String value) {
            addCriterion("depository =", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryNotEqualTo(String value) {
            addCriterion("depository <>", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryGreaterThan(String value) {
            addCriterion("depository >", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryGreaterThanOrEqualTo(String value) {
            addCriterion("depository >=", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryLessThan(String value) {
            addCriterion("depository <", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryLessThanOrEqualTo(String value) {
            addCriterion("depository <=", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryLike(String value) {
            addCriterion("depository like", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryNotLike(String value) {
            addCriterion("depository not like", value, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryIn(List<String> values) {
            addCriterion("depository in", values, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryNotIn(List<String> values) {
            addCriterion("depository not in", values, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryBetween(String value1, String value2) {
            addCriterion("depository between", value1, value2, "depository");
            return (Criteria) this;
        }

        public Criteria andDepositoryNotBetween(String value1, String value2) {
            addCriterion("depository not between", value1, value2, "depository");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopPriceIsNull() {
            addCriterion("shop_price is null");
            return (Criteria) this;
        }

        public Criteria andShopPriceIsNotNull() {
            addCriterion("shop_price is not null");
            return (Criteria) this;
        }

        public Criteria andShopPriceEqualTo(BigDecimal value) {
            addCriterion("shop_price =", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotEqualTo(BigDecimal value) {
            addCriterion("shop_price <>", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceGreaterThan(BigDecimal value) {
            addCriterion("shop_price >", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_price >=", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLessThan(BigDecimal value) {
            addCriterion("shop_price <", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_price <=", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceIn(List<BigDecimal> values) {
            addCriterion("shop_price in", values, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotIn(List<BigDecimal> values) {
            addCriterion("shop_price not in", values, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_price between", value1, value2, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_price not between", value1, value2, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopNumberIsNull() {
            addCriterion("shop_number is null");
            return (Criteria) this;
        }

        public Criteria andShopNumberIsNotNull() {
            addCriterion("shop_number is not null");
            return (Criteria) this;
        }

        public Criteria andShopNumberEqualTo(Long value) {
            addCriterion("shop_number =", value, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberNotEqualTo(Long value) {
            addCriterion("shop_number <>", value, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberGreaterThan(Long value) {
            addCriterion("shop_number >", value, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_number >=", value, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberLessThan(Long value) {
            addCriterion("shop_number <", value, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberLessThanOrEqualTo(Long value) {
            addCriterion("shop_number <=", value, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberIn(List<Long> values) {
            addCriterion("shop_number in", values, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberNotIn(List<Long> values) {
            addCriterion("shop_number not in", values, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberBetween(Long value1, Long value2) {
            addCriterion("shop_number between", value1, value2, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andShopNumberNotBetween(Long value1, Long value2) {
            addCriterion("shop_number not between", value1, value2, "shopNumber");
            return (Criteria) this;
        }

        public Criteria andPriceTotalIsNull() {
            addCriterion("price_total is null");
            return (Criteria) this;
        }

        public Criteria andPriceTotalIsNotNull() {
            addCriterion("price_total is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTotalEqualTo(BigDecimal value) {
            addCriterion("price_total =", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalNotEqualTo(BigDecimal value) {
            addCriterion("price_total <>", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalGreaterThan(BigDecimal value) {
            addCriterion("price_total >", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price_total >=", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalLessThan(BigDecimal value) {
            addCriterion("price_total <", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price_total <=", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalIn(List<BigDecimal> values) {
            addCriterion("price_total in", values, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalNotIn(List<BigDecimal> values) {
            addCriterion("price_total not in", values, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_total between", value1, value2, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_total not between", value1, value2, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andSpecsIsNull() {
            addCriterion("specs is null");
            return (Criteria) this;
        }

        public Criteria andSpecsIsNotNull() {
            addCriterion("specs is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsEqualTo(String value) {
            addCriterion("specs =", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsNotEqualTo(String value) {
            addCriterion("specs <>", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsGreaterThan(String value) {
            addCriterion("specs >", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsGreaterThanOrEqualTo(String value) {
            addCriterion("specs >=", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsLessThan(String value) {
            addCriterion("specs <", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsLessThanOrEqualTo(String value) {
            addCriterion("specs <=", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsLike(String value) {
            addCriterion("specs like", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsNotLike(String value) {
            addCriterion("specs not like", value, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsIn(List<String> values) {
            addCriterion("specs in", values, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsNotIn(List<String> values) {
            addCriterion("specs not in", values, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsBetween(String value1, String value2) {
            addCriterion("specs between", value1, value2, "specs");
            return (Criteria) this;
        }

        public Criteria andSpecsNotBetween(String value1, String value2) {
            addCriterion("specs not between", value1, value2, "specs");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(LocalDateTime value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(LocalDateTime value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(LocalDateTime value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(LocalDateTime value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<LocalDateTime> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<LocalDateTime> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andInUserIsNull() {
            addCriterion("in_user is null");
            return (Criteria) this;
        }

        public Criteria andInUserIsNotNull() {
            addCriterion("in_user is not null");
            return (Criteria) this;
        }

        public Criteria andInUserEqualTo(String value) {
            addCriterion("in_user =", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotEqualTo(String value) {
            addCriterion("in_user <>", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserGreaterThan(String value) {
            addCriterion("in_user >", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserGreaterThanOrEqualTo(String value) {
            addCriterion("in_user >=", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserLessThan(String value) {
            addCriterion("in_user <", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserLessThanOrEqualTo(String value) {
            addCriterion("in_user <=", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserLike(String value) {
            addCriterion("in_user like", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotLike(String value) {
            addCriterion("in_user not like", value, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserIn(List<String> values) {
            addCriterion("in_user in", values, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotIn(List<String> values) {
            addCriterion("in_user not in", values, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserBetween(String value1, String value2) {
            addCriterion("in_user between", value1, value2, "inUser");
            return (Criteria) this;
        }

        public Criteria andInUserNotBetween(String value1, String value2) {
            addCriterion("in_user not between", value1, value2, "inUser");
            return (Criteria) this;
        }

        public Criteria andShopSupplierIsNull() {
            addCriterion("shop_supplier is null");
            return (Criteria) this;
        }

        public Criteria andShopSupplierIsNotNull() {
            addCriterion("shop_supplier is not null");
            return (Criteria) this;
        }

        public Criteria andShopSupplierEqualTo(String value) {
            addCriterion("shop_supplier =", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierNotEqualTo(String value) {
            addCriterion("shop_supplier <>", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierGreaterThan(String value) {
            addCriterion("shop_supplier >", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("shop_supplier >=", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierLessThan(String value) {
            addCriterion("shop_supplier <", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierLessThanOrEqualTo(String value) {
            addCriterion("shop_supplier <=", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierLike(String value) {
            addCriterion("shop_supplier like", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierNotLike(String value) {
            addCriterion("shop_supplier not like", value, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierIn(List<String> values) {
            addCriterion("shop_supplier in", values, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierNotIn(List<String> values) {
            addCriterion("shop_supplier not in", values, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierBetween(String value1, String value2) {
            addCriterion("shop_supplier between", value1, value2, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andShopSupplierNotBetween(String value1, String value2) {
            addCriterion("shop_supplier not between", value1, value2, "shopSupplier");
            return (Criteria) this;
        }

        public Criteria andIsInspectionIsNull() {
            addCriterion("is_inspection is null");
            return (Criteria) this;
        }

        public Criteria andIsInspectionIsNotNull() {
            addCriterion("is_inspection is not null");
            return (Criteria) this;
        }

        public Criteria andIsInspectionEqualTo(Integer value) {
            addCriterion("is_inspection =", value, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionNotEqualTo(Integer value) {
            addCriterion("is_inspection <>", value, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionGreaterThan(Integer value) {
            addCriterion("is_inspection >", value, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_inspection >=", value, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionLessThan(Integer value) {
            addCriterion("is_inspection <", value, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionLessThanOrEqualTo(Integer value) {
            addCriterion("is_inspection <=", value, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionIn(List<Integer> values) {
            addCriterion("is_inspection in", values, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionNotIn(List<Integer> values) {
            addCriterion("is_inspection not in", values, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionBetween(Integer value1, Integer value2) {
            addCriterion("is_inspection between", value1, value2, "isInspection");
            return (Criteria) this;
        }

        public Criteria andIsInspectionNotBetween(Integer value1, Integer value2) {
            addCriterion("is_inspection not between", value1, value2, "isInspection");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(LocalDateTime value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(LocalDateTime value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(LocalDateTime value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(LocalDateTime value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<LocalDateTime> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<LocalDateTime> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}