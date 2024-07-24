import {
  getFilters,
  deleteFilter,
  saveFilter,
  updateFilter,
} from '../api/filter-api.js';

const state = {
  filters: [],
  totalFilters: 0,
  currentPage: 1,
  pageSize: 10,
};

const getters = {
  allFilters: (state) => state.filters,
  totalFilters: (state) => state.totalFilters,
  currentPage: (state) => state.currentPage,
  pageSize: (state) => state.pageSize,
};

const actions = {
  async fetchFilters(
    { commit, state },
    { page = 1, size = state.pageSize } = {}
  ) {
    try {
      const response = await getFilters(page - 1, size);
      commit('setFilters', response.data.content);
      commit('setTotalFilters', response.data.totalElements);
      commit('setCurrentPage', page);
      commit('setPageSize', size);
    } catch (error) {
      console.error('Failed to fetch filters:', error);
    }
  },
  async deleteFilter({ commit, dispatch }, id) {
    try {
      await deleteFilter(id);
      dispatch('fetchFilters', {
        page: state.currentPage,
        size: state.pageSize,
      });
    } catch (error) {
      console.error('Failed to delete filter:', error);
    }
  },
  async saveFilter({ dispatch }, filter) {
    try {
      await saveFilter(filter);
      dispatch('fetchFilters', {
        page: state.currentPage,
        size: state.pageSize,
      });
    } catch (error) {
      console.error('Failed to save filter:', error);
    }
  },
  async updateFilter({ dispatch }, filter) {
    try {
      await updateFilter(filter);
      dispatch('fetchFilters', {
        page: state.currentPage,
        size: state.pageSize,
      });
    } catch (error) {
      console.error('Failed to update filter:', error);
    }
  },
};

const mutations = {
  setFilters(state, filters) {
    state.filters = filters;
  },
  setTotalFilters(state, total) {
    state.totalFilters = total;
  },
  setCurrentPage(state, page) {
    state.currentPage = page;
  },
  setPageSize(state, size) {
    state.pageSize = size;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
