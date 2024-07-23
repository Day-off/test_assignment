<template>
  <div class="p-4">
    <h1 class="text-2xl font-bold mb-4">Filters</h1>

    <div class="flex justify-between items-center mb-4">
      <div v-if="showButton">
        <AppButton @click="openCreateFilterForm">Create New Filter</AppButton>
      </div>
      <div class="ml-auto">
        <AppButton @click="toggleModalMode" :color="getToggleButtonColor">
          {{ modalMode ? 'Switch to Non-Modal Mode' : 'Switch to Modal Mode' }}
        </AppButton>
      </div>
    </div>

    <FilterModal
      v-if="showFilterForm && modalMode"
      :filterData="selectedFilter"
      @close="closeFilterForm"
      :is-update-filter="isUpdate"
    />

    <FilterDialog
      v-if="showFilterForm && !modalMode"
      :key="selectedFilter?.id"
      :filterData="selectedFilter"
      @close="closeFilterForm"
      :is-update-filter="isUpdate"
    />

    <FilterTable
      :filters="allFilters"
      @edit-filter="openUpdateFilterForm"
      @delete-filter="deleteFilterById"
    />
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import FilterModal from '@/page/modal/filter-creation-modal.vue';
import FilterDialog from '@/page/molecules/filter-creation-dialog.vue';
import FilterTable from '@/page/molecules/filter-table.vue';
import AppButton from '@/ui/button/app-button.vue';

export default {
  name: 'FilterPage',
  components: {
    AppButton,
    FilterDialog,
    FilterModal,
    FilterTable,
  },
  data() {
    return {
      showFilterForm: false,
      modalMode: true,
      selectedFilter: null,
    };
  },
  computed: {
    ...mapGetters('useFilterStore', ['allFilters']),

    isUpdate() {
      return this.selectedFilter?.id !== undefined;
    },

    getToggleButtonColor() {
      return this.modalMode ? 'gray' : 'gray-outline';
    },

    showButton() {
      return !(!this.modalMode && this.showFilterForm);
    },
  },
  methods: {
    ...mapActions('useFilterStore', ['fetchFilters', 'deleteFilter']),
    ...mapActions('useCriterionStore', [
      'fetchCriterionTypes',
      'fetchComparingConditionTypes',
    ]),

    async fetchInitialData() {
      await this.fetchCriterionTypes();
      await this.fetchComparingConditionTypes();
    },

    async deleteFilterById(id) {
      if (confirm('Are you sure you want to delete this filter?')) {
        await this.deleteFilter(id);
      }
    },

    openCreateFilterForm() {
      this.selectedFilter = this.createEmptyFilter();
      this.showFilterForm = true;
    },

    openUpdateFilterForm(filter) {
      this.selectedFilter = { ...filter };
      this.showFilterForm = true;
    },

    closeFilterForm() {
      this.showFilterForm = false;
    },

    toggleModalMode() {
      this.modalMode = !this.modalMode;
    },

    createEmptyFilter() {
      return {
        name: '',
        criteria: [
          {
            criterionType: { id: 1, name: 'Amount' },
            comparingConditionType: {
              id: 2,
              conditionName: 'Less',
              criterionTypeId: 1,
            },
            conditionValue: '',
          },
        ],
      };
    },
  },
  async created() {
    await this.fetchFilters();
    await this.fetchInitialData();
  },
};
</script>
