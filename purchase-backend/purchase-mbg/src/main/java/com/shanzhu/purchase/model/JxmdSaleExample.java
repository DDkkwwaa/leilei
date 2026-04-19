package com.shanzhu.purchase.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JxmdSaleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JxmdSaleExample() {
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

        public Criteria andSaleNumberIsNull() {
            addCriterion("sale_Number is null");
            return (Criteria) this;
        }

        public Criteria andSaleNumberIsNotNull() {
            addCriterion("sale_Number is not null");
            return (Criteria) this;
        }

        public Criteria andSaleNumberEqualTo(String value) {
            addCriterion("sale_Number =", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotEqualTo(String value) {
            addCriterion("sale_Number <>", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberGreaterThan(String value) {
            addCriterion("sale_Number >", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberGreaterThanOrEqualTo(String value) {
            addCriterion("sale_Number >=", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberLessThan(String value) {
            addCriterion("sale_Number <", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberLessThanOrEqualTo(String value) {
            addCriterion("sale_Number <=", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberLike(String value) {
            addCriterion("sale_Number like", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotLike(String value) {
            addCriterion("sale_Number not like", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberIn(List<String> values) {
            addCriterion("sale_Number in", values, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotIn(List<String> values) {
            addCriterion("sale_Number not in", values, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberBetween(String value1, String value2) {
            addCriterion("sale_Number between", value1, value2, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotBetween(String value1, String value2) {
            addCriterion("sale_Number not between", value1, value2, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleUserIsNull() {
            addCriterion("sale_user is null");
            return (Criteria) this;
        }

        public Criteria andSaleUserIsNotNull() {
            addCriterion("sale_user is not null");
            return (Criteria) this;
        }

        public Criteria andSaleUserEqualTo(String value) {
            addCriterion("sale_user =", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserNotEqualTo(String value) {
            addCriterion("sale_user <>", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserGreaterThan(String value) {
            addCriterion("sale_user >", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserGreaterThanOrEqualTo(String value) {
            addCriterion("sale_user >=", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserLessThan(String value) {
            addCriterion("sale_user <", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserLessThanOrEqualTo(String value) {
            addCriterion("sale_user <=", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserLike(String value) {
            addCriterion("sale_user like", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserNotLike(String value) {
            addCriterion("sale_user not like", value, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserIn(List<String> values) {
            addCriterion("sale_user in", values, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserNotIn(List<String> values) {
            addCriterion("sale_user not in", values, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserBetween(String value1, String value2) {
            addCriterion("sale_user between", value1, value2, "saleUser");
            return (Criteria) this;
        }

        public Criteria andSaleUserNotBetween(String value1, String value2) {
            addCriterion("sale_user not between", value1, value2, "saleUser");
            return (Criteria) this;
        }

        public Criteria andShopIsNull() {
            addCriterion("shop is null");
            return (Criteria) this;
        }

        public Criteria andShopIsNotNull() {
            addCriterion("shop is not null");
            return (Criteria) this;
        }

        public Criteria andShopEqualTo(String value) {
            addCriterion("shop =", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopNotEqualTo(String value) {
            addCriterion("shop <>", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopGreaterThan(String value) {
            addCriterion("shop >", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopGreaterThanOrEqualTo(String value) {
            addCriterion("shop >=", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopLessThan(String value) {
            addCriterion("shop <", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopLessThanOrEqualTo(String value) {
            addCriterion("shop <=", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopLike(String value) {
            addCriterion("shop like", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopNotLike(String value) {
            addCriterion("shop not like", value, "shop");
            return (Criteria) this;
        }

        public Criteria andShopIn(List<String> values) {
            addCriterion("shop in", values, "shop");
            return (Criteria) this;
        }

        public Criteria andShopNotIn(List<String> values) {
            addCriterion("shop not in", values, "shop");
            return (Criteria) this;
        }

        public Criteria andShopBetween(String value1, String value2) {
            addCriterion("shop between", value1, value2, "shop");
            return (Criteria) this;
        }

        public Criteria andShopNotBetween(String value1, String value2) {
            addCriterion("shop not between", value1, value2, "shop");
            return (Criteria) this;
        }

        public Criteria andShopTypeIsNull() {
            addCriterion("shop_type is null");
            return (Criteria) this;
        }

        public Criteria andShopTypeIsNotNull() {
            addCriterion("shop_type is not null");
            return (Criteria) this;
        }

        public Criteria andShopTypeEqualTo(String value) {
            addCriterion("shop_type =", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotEqualTo(String value) {
            addCriterion("shop_type <>", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeGreaterThan(String value) {
            addCriterion("shop_type >", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_type >=", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLessThan(String value) {
            addCriterion("shop_type <", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLessThanOrEqualTo(String value) {
            addCriterion("shop_type <=", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLike(String value) {
            addCriterion("shop_type like", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotLike(String value) {
            addCriterion("shop_type not like", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeIn(List<String> values) {
            addCriterion("shop_type in", values, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotIn(List<String> values) {
            addCriterion("shop_type not in", values, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeBetween(String value1, String value2) {
            addCriterion("shop_type between", value1, value2, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotBetween(String value1, String value2) {
            addCriterion("shop_type not between", value1, value2, "shopType");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Long value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Long value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Long value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Long value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Long value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Long value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Long> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Long> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Long value1, Long value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Long value1, Long value2) {
            addCriterion("num not between", value1, value2, "num");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(BigDecimal value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(BigDecimal value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<BigDecimal> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(LocalDateTime value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(LocalDateTime value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(LocalDateTime value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(LocalDateTime value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<LocalDateTime> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<LocalDateTime> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("time not between", value1, value2, "time");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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