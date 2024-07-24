<template>
  <div class="bg-white p-6 w-3/5">
    <div class="dialog-content bg-white pt-6 pb-6 rounded-lg w-full max-w-4xl">
      <h2 v-if="isUpdateFilter" class="text-lg font-semibold mb-4">
        Edit Filter
      </h2>
      <h2 v-else class="text-lg font-semibold mb-4">Create Filter</h2>
      <FilterCreationForm
        :is-update="isUpdateFilter"
        :filter-data="filterData"
        @close="closeDialog"
      />
    </div>
  </div>
</template>

<script>
import FilterCreationForm from '@/page/atoms/filter-creation-form.vue';

export default {
  name: 'FilterDialog',
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
  methods: {
    closeDialog() {
      this.$emit('close');
    },
  },
};
</script>
<style scoped>
.dialog-content {
  background: white;
  padding: 1.5rem;
  border-radius: 0.2rem;
  width: 50%;
  border: gray solid 3px;
  max-height: 80vh;
  overflow-y: auto;
  resize: vertical;
}
</style>
