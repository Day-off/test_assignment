import {getFilters, deleteFilter, saveFilter, updateFilter} from '../api/filter-api.js';

const state = {
    filters: [],
};

const getters = {
    allFilters: (state) => state.filters,
};

const actions = {
    async fetchFilters({commit}) {
        try {
            const response = await getFilters();
            commit('setFilters', response.data);
        } catch (error) {
            console.error('Failed to fetch filters:', error);
        }
    },
    async deleteFilter({commit, dispatch}, id) {
        try {
            await deleteFilter(id);
            dispatch('fetchFilters');
        } catch (error) {
            console.error('Failed to delete filter:', error);
        }
    },
    async saveFilter({commit, dispatch}, filter) {
        try {
            await saveFilter(filter);
            dispatch('fetchFilters');
        } catch (error) {
            console.error('Failed to save filter:', error);
        }
    },
    async updateFilter({commit, dispatch}, filter) {
        try {
            await updateFilter(filter)
            dispatch('fetchFilters');
        } catch (error) {
            console.error('Failed to update filter:', error);
        }
    },
};

const mutations = {
    setFilters(state, filters) {
        state.filters = filters;
    },
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
