<template>
  <div
    class="fixed inset-0 bg-gray-500 bg-opacity-75 flex justify-center items-center z-50"
  >
    <div ref="modalContent" class="modal-content">
      <h2 v-if="isUpdateFilter" class="text-lg font-semibold mb-4">
        Edit Filter
      </h2>
      <h2 class="text-lg font-semibold mb-4">Create Filter</h2>
      <FilterCreationForm
        :is-update="isUpdateFilter"
        :filter-data="filter"
        @close="closeModal"
      />
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import FilterCreationForm from '@/page/atoms/filter-creation-form.vue';

export default {
  name: 'FilterModal',
  components: { FilterCreationForm },
  props: {
    filterData: {
      type: Object,
      required: true,
    },
    isUpdateFilter: {
      type: Boolean,
      required: false,
    },
  },
  data() {
    return {
      filter: this.filterData,
    };
  },
  methods: {
    ...mapActions('useCriterionStore', [
      'fetchCriterionTypes',
      'fetchComparingConditionTypes',
    ]),

    async fetchInitialData() {
      await this.fetchCriterionTypes();
      await this.fetchComparingConditionTypes();
    },

    closeModal() {
      document.removeEventListener('click', this.handleClickOutside);
      this.$emit('close');
    },

    handleClickOutside(event) {
      if (
        this.$refs.modalContent &&
        !this.$refs.modalContent.contains(event.target)
      ) {
        this.closeModal();
      }
    },
  },
  async created() {
    await this.fetchInitialData();
    document.addEventListener('click', this.handleClickOutside);
  },
  beforeDestroy() {
    document.removeEventListener('click', this.handleClickOutside);
  },
};
</script>

<style scoped>
.fixed {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 50;
}

.modal-content {
  background: white;
  padding: 1.5rem;
  border-radius: 0.5rem;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 50%;
  max-height: 80vh;
  overflow-y: auto;
  resize: vertical;
}
</style>
