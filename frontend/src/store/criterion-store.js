import {getCriterionConditionTypes, getCriterionTypes} from "@/api/criterion-api.js";

const state = {
    criterionTypes: [],
    comparingConditionTypes: [],
};

const mutations = {
    setCriterionTypes(state, types) {
        state.criterionTypes = types;
    },
    setComparingConditionTypes(state, types) {
        state.comparingConditionTypes = types;
    },
};

const actions = {
    async fetchCriterionTypes({ commit }) {
        try {
            const response = await getCriterionTypes();
            commit('setCriterionTypes', response.data);
        } catch (error) {
            console.error('Failed to fetch criterion types:', error);
        }
    },
    async fetchComparingConditionTypes({ commit }) {
        try {
            const response = await getCriterionConditionTypes();
            commit('setComparingConditionTypes', response.data);
        } catch (error) {
            console.error('Failed to fetch comparing condition types:', error);
        }
    },
};

const getters = {
    criterionTypes: (state) => state.criterionTypes,
    comparingConditionTypes: (state) => state.comparingConditionTypes,
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
};
