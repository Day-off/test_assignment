<template>
  <div>
    <div class="mb-4">
      <label for="filterName" class="block text-gray-700">Filter Name</label>
      <input
        v-model="filter.name"
        type="text"
        id="filterName"
        class="border rounded-md px-3 py-2"
      />
      <span v-if="!isNameValid" class="text-red-500 text-sm block mt-1"
        >Filter name is required.</span
      >
    </div>

    <Criterion
      v-for="(criterion, index) in filter.criteria"
      :key="index"
      :criterion="criterion"
      :index="index"
      :criterionTypes="criterionTypes"
      :getComparingConditionTypes="getComparingConditionTypes"
      :isCriterionValid="isCriterionValid"
      @remove="removeCriterion"
      @update="updateConditionTypes"
    />

    <div class="mb-4">
      <span v-if="!hasCriteria" class="text-red-500 text-sm"
        >Filter should contain at least one criterion.</span
      >
    </div>

    <AppButton color="gray" @click="addCriterion">Add Criterion</AppButton>

    <div class="flex justify-between pt-20">
      <AppButton color="gray-outline" @click="closeModal">Cancel</AppButton>
      <AppButton @click="handleSubmit" color="green" :disabled="!isFormValid"
        >Save</AppButton
      >
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import AppButton from '@/ui/button/app-button.vue';
import Criterion from '@/page/atoms/criterion-component.vue';

export default {
  name: 'FilterCreationForm',
  components: { AppButton, Criterion },
  props: {
    filterData: {
      type: Object,
      required: true,
    },
    isUpdate: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      filter: { ...this.filterData },
    };
  },
  computed: {
    ...mapGetters('useCriterionStore', [
      'criterionTypes',
      'comparingConditionTypes',
    ]),

    isNameValid() {
      return this.filter.name.trim() !== '';
    },

    hasCriteria() {
      return this.filter.criteria.length > 0;
    },

    isFormValid() {
      return (
        this.isNameValid &&
        this.hasCriteria &&
        this.filter.criteria.every(this.isCriterionValid)
      );
    },
  },
  methods: {
    ...mapActions('useFilterStore', ['saveFilter', 'updateFilter']),

    handleSubmit() {
      if (this.isFormValid) {
        try {
          if (this.isUpdate) {
            this.updateFilter(this.filter);
          } else {
            this.saveFilter(this.filter);
          }
          this.resetForm();
          this.$emit('close');
        } catch (error) {
          console.error('Failed to save filter:', error);
        }
      }
    },

    closeModal() {
      this.resetForm();
      this.$emit('close');
    },

    addCriterion() {
      const defaultCriterionType = this.criterionTypes[0];
      const defaultConditionType =
        this.getComparingConditionTypes(defaultCriterionType?.id)[0] || {};

      if (defaultCriterionType) {
        this.filter.criteria.push({
          criterionType: defaultCriterionType,
          comparingConditionType: defaultConditionType,
          conditionValue: '',
        });
      } else {
        alert('No criterion types available.');
      }
    },

    removeCriterion(index) {
      this.filter.criteria.splice(index, 1);
    },

    updateConditionTypes(index) {
      const criterion = this.filter.criteria[index];
      criterion.comparingConditionType =
        this.getComparingConditionTypes(criterion.criterionType?.id)[0] || {};
    },

    getComparingConditionTypes(criterionTypeId) {
      return this.comparingConditionTypes.filter(
        (condition) => condition.criterionTypeId === criterionTypeId
      );
    },

    isCriterionValid(criterion) {
      return criterion.conditionValue?.toString().trim() !== '';
    },

    resetForm() {
      this.filter = { name: '', criteria: [] };
    },
  },
};
</script>
