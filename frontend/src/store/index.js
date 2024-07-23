import { createStore } from 'vuex';
import filterStore from './filter-store';
import criterionStore from './criterion-store';

export default createStore({
    modules: {
        useFilterStore: filterStore,
        useCriterionStore: criterionStore,
    },
});