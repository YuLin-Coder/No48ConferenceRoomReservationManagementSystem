package com.hbu.entity;

import java.util.ArrayList;
import java.util.List;

public class TConferenceRoomAppointmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TConferenceRoomAppointmentExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Long value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Long value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Long value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Long value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Long value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Long> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Long> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Long value1, Long value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Long value1, Long value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdIsNull() {
            addCriterion("conference_room_id is null");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdIsNotNull() {
            addCriterion("conference_room_id is not null");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdEqualTo(Long value) {
            addCriterion("conference_room_id =", value, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdNotEqualTo(Long value) {
            addCriterion("conference_room_id <>", value, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdGreaterThan(Long value) {
            addCriterion("conference_room_id >", value, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdGreaterThanOrEqualTo(Long value) {
            addCriterion("conference_room_id >=", value, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdLessThan(Long value) {
            addCriterion("conference_room_id <", value, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdLessThanOrEqualTo(Long value) {
            addCriterion("conference_room_id <=", value, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdIn(List<Long> values) {
            addCriterion("conference_room_id in", values, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdNotIn(List<Long> values) {
            addCriterion("conference_room_id not in", values, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdBetween(Long value1, Long value2) {
            addCriterion("conference_room_id between", value1, value2, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andConferenceRoomIdNotBetween(Long value1, Long value2) {
            addCriterion("conference_room_id not between", value1, value2, "conferenceRoomId");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNull() {
            addCriterion("max_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNotNull() {
            addCriterion("max_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNumEqualTo(Integer value) {
            addCriterion("max_num =", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotEqualTo(Integer value) {
            addCriterion("max_num <>", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThan(Integer value) {
            addCriterion("max_num >", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_num >=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThan(Integer value) {
            addCriterion("max_num <", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThanOrEqualTo(Integer value) {
            addCriterion("max_num <=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumIn(List<Integer> values) {
            addCriterion("max_num in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotIn(List<Integer> values) {
            addCriterion("max_num not in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumBetween(Integer value1, Integer value2) {
            addCriterion("max_num between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("max_num not between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andThemeIsNull() {
            addCriterion("theme is null");
            return (Criteria) this;
        }

        public Criteria andThemeIsNotNull() {
            addCriterion("theme is not null");
            return (Criteria) this;
        }

        public Criteria andThemeEqualTo(String value) {
            addCriterion("theme =", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotEqualTo(String value) {
            addCriterion("theme <>", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeGreaterThan(String value) {
            addCriterion("theme >", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeGreaterThanOrEqualTo(String value) {
            addCriterion("theme >=", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLessThan(String value) {
            addCriterion("theme <", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLessThanOrEqualTo(String value) {
            addCriterion("theme <=", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLike(String value) {
            addCriterion("theme like", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotLike(String value) {
            addCriterion("theme not like", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeIn(List<String> values) {
            addCriterion("theme in", values, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotIn(List<String> values) {
            addCriterion("theme not in", values, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeBetween(String value1, String value2) {
            addCriterion("theme between", value1, value2, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotBetween(String value1, String value2) {
            addCriterion("theme not between", value1, value2, "theme");
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

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
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

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeIsNull() {
            addCriterion("starttime_code is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeIsNotNull() {
            addCriterion("starttime_code is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeEqualTo(Integer value) {
            addCriterion("starttime_code =", value, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeNotEqualTo(Integer value) {
            addCriterion("starttime_code <>", value, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeGreaterThan(Integer value) {
            addCriterion("starttime_code >", value, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("starttime_code >=", value, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeLessThan(Integer value) {
            addCriterion("starttime_code <", value, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeLessThanOrEqualTo(Integer value) {
            addCriterion("starttime_code <=", value, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeIn(List<Integer> values) {
            addCriterion("starttime_code in", values, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeNotIn(List<Integer> values) {
            addCriterion("starttime_code not in", values, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeBetween(Integer value1, Integer value2) {
            addCriterion("starttime_code between", value1, value2, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andStarttimeCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("starttime_code not between", value1, value2, "starttimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeIsNull() {
            addCriterion("endtime_code is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeIsNotNull() {
            addCriterion("endtime_code is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeEqualTo(Integer value) {
            addCriterion("endtime_code =", value, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeNotEqualTo(Integer value) {
            addCriterion("endtime_code <>", value, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeGreaterThan(Integer value) {
            addCriterion("endtime_code >", value, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("endtime_code >=", value, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeLessThan(Integer value) {
            addCriterion("endtime_code <", value, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeLessThanOrEqualTo(Integer value) {
            addCriterion("endtime_code <=", value, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeIn(List<Integer> values) {
            addCriterion("endtime_code in", values, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeNotIn(List<Integer> values) {
            addCriterion("endtime_code not in", values, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeBetween(Integer value1, Integer value2) {
            addCriterion("endtime_code between", value1, value2, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andEndtimeCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("endtime_code not between", value1, value2, "endtimeCode");
            return (Criteria) this;
        }

        public Criteria andExamineStatusIsNull() {
            addCriterion("examine_status is null");
            return (Criteria) this;
        }

        public Criteria andExamineStatusIsNotNull() {
            addCriterion("examine_status is not null");
            return (Criteria) this;
        }

        public Criteria andExamineStatusEqualTo(Integer value) {
            addCriterion("examine_status =", value, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusNotEqualTo(Integer value) {
            addCriterion("examine_status <>", value, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusGreaterThan(Integer value) {
            addCriterion("examine_status >", value, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("examine_status >=", value, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusLessThan(Integer value) {
            addCriterion("examine_status <", value, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusLessThanOrEqualTo(Integer value) {
            addCriterion("examine_status <=", value, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusIn(List<Integer> values) {
            addCriterion("examine_status in", values, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusNotIn(List<Integer> values) {
            addCriterion("examine_status not in", values, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusBetween(Integer value1, Integer value2) {
            addCriterion("examine_status between", value1, value2, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("examine_status not between", value1, value2, "examineStatus");
            return (Criteria) this;
        }

        public Criteria andExamineReasonIsNull() {
            addCriterion("examine_reason is null");
            return (Criteria) this;
        }

        public Criteria andExamineReasonIsNotNull() {
            addCriterion("examine_reason is not null");
            return (Criteria) this;
        }

        public Criteria andExamineReasonEqualTo(String value) {
            addCriterion("examine_reason =", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonNotEqualTo(String value) {
            addCriterion("examine_reason <>", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonGreaterThan(String value) {
            addCriterion("examine_reason >", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonGreaterThanOrEqualTo(String value) {
            addCriterion("examine_reason >=", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonLessThan(String value) {
            addCriterion("examine_reason <", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonLessThanOrEqualTo(String value) {
            addCriterion("examine_reason <=", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonLike(String value) {
            addCriterion("examine_reason like", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonNotLike(String value) {
            addCriterion("examine_reason not like", value, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonIn(List<String> values) {
            addCriterion("examine_reason in", values, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonNotIn(List<String> values) {
            addCriterion("examine_reason not in", values, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonBetween(String value1, String value2) {
            addCriterion("examine_reason between", value1, value2, "examineReason");
            return (Criteria) this;
        }

        public Criteria andExamineReasonNotBetween(String value1, String value2) {
            addCriterion("examine_reason not between", value1, value2, "examineReason");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("isdel is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("isdel is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(Boolean value) {
            addCriterion("isdel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Boolean value) {
            addCriterion("isdel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Boolean value) {
            addCriterion("isdel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isdel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Boolean value) {
            addCriterion("isdel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Boolean value) {
            addCriterion("isdel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Boolean> values) {
            addCriterion("isdel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Boolean> values) {
            addCriterion("isdel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Boolean value1, Boolean value2) {
            addCriterion("isdel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isdel not between", value1, value2, "isdel");
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