package com.leaforbook.orange.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrangeLogisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrangeLogisticsExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andLogisticsIdIsNull() {
            addCriterion("logistics_id is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdIsNotNull() {
            addCriterion("logistics_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdEqualTo(String value) {
            addCriterion("logistics_id =", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdNotEqualTo(String value) {
            addCriterion("logistics_id <>", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdGreaterThan(String value) {
            addCriterion("logistics_id >", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdGreaterThanOrEqualTo(String value) {
            addCriterion("logistics_id >=", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdLessThan(String value) {
            addCriterion("logistics_id <", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdLessThanOrEqualTo(String value) {
            addCriterion("logistics_id <=", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdLike(String value) {
            addCriterion("logistics_id like", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdNotLike(String value) {
            addCriterion("logistics_id not like", value, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdIn(List<String> values) {
            addCriterion("logistics_id in", values, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdNotIn(List<String> values) {
            addCriterion("logistics_id not in", values, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdBetween(String value1, String value2) {
            addCriterion("logistics_id between", value1, value2, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andLogisticsIdNotBetween(String value1, String value2) {
            addCriterion("logistics_id not between", value1, value2, "logisticsId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPostidIsNull() {
            addCriterion("postid is null");
            return (Criteria) this;
        }

        public Criteria andPostidIsNotNull() {
            addCriterion("postid is not null");
            return (Criteria) this;
        }

        public Criteria andPostidEqualTo(String value) {
            addCriterion("postid =", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotEqualTo(String value) {
            addCriterion("postid <>", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidGreaterThan(String value) {
            addCriterion("postid >", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidGreaterThanOrEqualTo(String value) {
            addCriterion("postid >=", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLessThan(String value) {
            addCriterion("postid <", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLessThanOrEqualTo(String value) {
            addCriterion("postid <=", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLike(String value) {
            addCriterion("postid like", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotLike(String value) {
            addCriterion("postid not like", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidIn(List<String> values) {
            addCriterion("postid in", values, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotIn(List<String> values) {
            addCriterion("postid not in", values, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidBetween(String value1, String value2) {
            addCriterion("postid between", value1, value2, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotBetween(String value1, String value2) {
            addCriterion("postid not between", value1, value2, "postid");
            return (Criteria) this;
        }

        public Criteria andDateCreateIsNull() {
            addCriterion("date_create is null");
            return (Criteria) this;
        }

        public Criteria andDateCreateIsNotNull() {
            addCriterion("date_create is not null");
            return (Criteria) this;
        }

        public Criteria andDateCreateEqualTo(Date value) {
            addCriterion("date_create =", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateNotEqualTo(Date value) {
            addCriterion("date_create <>", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateGreaterThan(Date value) {
            addCriterion("date_create >", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("date_create >=", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateLessThan(Date value) {
            addCriterion("date_create <", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateLessThanOrEqualTo(Date value) {
            addCriterion("date_create <=", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateIn(List<Date> values) {
            addCriterion("date_create in", values, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateNotIn(List<Date> values) {
            addCriterion("date_create not in", values, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateBetween(Date value1, Date value2) {
            addCriterion("date_create between", value1, value2, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateNotBetween(Date value1, Date value2) {
            addCriterion("date_create not between", value1, value2, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateIsNull() {
            addCriterion("date_update is null");
            return (Criteria) this;
        }

        public Criteria andDateUpdateIsNotNull() {
            addCriterion("date_update is not null");
            return (Criteria) this;
        }

        public Criteria andDateUpdateEqualTo(Date value) {
            addCriterion("date_update =", value, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateNotEqualTo(Date value) {
            addCriterion("date_update <>", value, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateGreaterThan(Date value) {
            addCriterion("date_update >", value, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("date_update >=", value, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateLessThan(Date value) {
            addCriterion("date_update <", value, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateLessThanOrEqualTo(Date value) {
            addCriterion("date_update <=", value, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateIn(List<Date> values) {
            addCriterion("date_update in", values, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateNotIn(List<Date> values) {
            addCriterion("date_update not in", values, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateBetween(Date value1, Date value2) {
            addCriterion("date_update between", value1, value2, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andDateUpdateNotBetween(Date value1, Date value2) {
            addCriterion("date_update not between", value1, value2, "dateUpdate");
            return (Criteria) this;
        }

        public Criteria andByCreateIsNull() {
            addCriterion("by_create is null");
            return (Criteria) this;
        }

        public Criteria andByCreateIsNotNull() {
            addCriterion("by_create is not null");
            return (Criteria) this;
        }

        public Criteria andByCreateEqualTo(String value) {
            addCriterion("by_create =", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateNotEqualTo(String value) {
            addCriterion("by_create <>", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateGreaterThan(String value) {
            addCriterion("by_create >", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateGreaterThanOrEqualTo(String value) {
            addCriterion("by_create >=", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateLessThan(String value) {
            addCriterion("by_create <", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateLessThanOrEqualTo(String value) {
            addCriterion("by_create <=", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateLike(String value) {
            addCriterion("by_create like", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateNotLike(String value) {
            addCriterion("by_create not like", value, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateIn(List<String> values) {
            addCriterion("by_create in", values, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateNotIn(List<String> values) {
            addCriterion("by_create not in", values, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateBetween(String value1, String value2) {
            addCriterion("by_create between", value1, value2, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByCreateNotBetween(String value1, String value2) {
            addCriterion("by_create not between", value1, value2, "byCreate");
            return (Criteria) this;
        }

        public Criteria andByUpdateIsNull() {
            addCriterion("by_update is null");
            return (Criteria) this;
        }

        public Criteria andByUpdateIsNotNull() {
            addCriterion("by_update is not null");
            return (Criteria) this;
        }

        public Criteria andByUpdateEqualTo(String value) {
            addCriterion("by_update =", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateNotEqualTo(String value) {
            addCriterion("by_update <>", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateGreaterThan(String value) {
            addCriterion("by_update >", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateGreaterThanOrEqualTo(String value) {
            addCriterion("by_update >=", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateLessThan(String value) {
            addCriterion("by_update <", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateLessThanOrEqualTo(String value) {
            addCriterion("by_update <=", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateLike(String value) {
            addCriterion("by_update like", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateNotLike(String value) {
            addCriterion("by_update not like", value, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateIn(List<String> values) {
            addCriterion("by_update in", values, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateNotIn(List<String> values) {
            addCriterion("by_update not in", values, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateBetween(String value1, String value2) {
            addCriterion("by_update between", value1, value2, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andByUpdateNotBetween(String value1, String value2) {
            addCriterion("by_update not between", value1, value2, "byUpdate");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNull() {
            addCriterion("data_status is null");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNotNull() {
            addCriterion("data_status is not null");
            return (Criteria) this;
        }

        public Criteria andDataStatusEqualTo(String value) {
            addCriterion("data_status =", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotEqualTo(String value) {
            addCriterion("data_status <>", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThan(String value) {
            addCriterion("data_status >", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThanOrEqualTo(String value) {
            addCriterion("data_status >=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThan(String value) {
            addCriterion("data_status <", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThanOrEqualTo(String value) {
            addCriterion("data_status <=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLike(String value) {
            addCriterion("data_status like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotLike(String value) {
            addCriterion("data_status not like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusIn(List<String> values) {
            addCriterion("data_status in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotIn(List<String> values) {
            addCriterion("data_status not in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusBetween(String value1, String value2) {
            addCriterion("data_status between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotBetween(String value1, String value2) {
            addCriterion("data_status not between", value1, value2, "dataStatus");
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