<template>
  <div class="mb-4 border p-4 rounded-md">
    <div class="flex items-center mb-2">
      <label class="mr-2">Criterion Type</label>
      <select
        v-model="criterion.criterionType"
        @change="updateCriterion"
        class="border rounded-md px-3 py-2"
      >
        <option v-for="type in criterionTypes" :key="type.id" :value="type">
          {{ type.name }}
        </option>
      </select>
    </div>

    <div class="flex items-center mb-2">
      <label class="mr-2">Condition Type</label>
      <select
        v-model="criterion.comparingConditionType"
        class="border rounded-md px-3 py-2"
      >
        <option
          v-for="condition in getComparingConditionTypes(
            criterion.criterionType?.id
          )"
          :key="condition.id"
          :value="condition"
        >
          {{ condition.conditionName }}
        </option>
      </select>
    </div>

    <div class="mb-2">
      <label :for="`conditionValue${index}`" class="block mb-3"
        >Condition Value</label
      >
      <input
        v-if="isNumberType(criterion.criterionType?.name)"
        v-model="criterion.conditionValueNumber"
        type="number"
        :id="`conditionValue${index}`"
        class="border rounded-md px-3 py-2"
      />
      <input
        v-if="isTextType(criterion.criterionType?.name)"
        v-model="criterion.conditionValueText"
        type="text"
        :id="`conditionValue${index}`"
        class="border rounded-md px-3 py-2"
      />
      <input
        v-if="isDateType(criterion.criterionType?.name)"
        v-model="criterion.conditionValueDate"
        type="date"
        :id="`conditionValue${index}`"
        class="border rounded-md px-3 py-2"
      />
      <span
        v-if="!isCriterionValid(criterion)"
        class="text-red-500 text-sm block mt-1"
        >Condition value is required.</span
      >
    </div>

    <div class="flex justify-end">
      <AppButton color="red-outline" @click.stop="$emit('remove', index)"
        >Remove Criterion
      </AppButton>
    </div>
  </div>
</template>

<script>
import AppButton from '@/ui/button/app-button.vue';

export default {
  name: 'Criterion',
  components: { AppButton },
  props: {
    criterion: {
      type: Object,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    },
    criterionTypes: {
      type: Array,
      required: true,
    },
    getComparingConditionTypes: {
      type: Function,
      required: true,
    },
    isCriterionValid: {
      type: Function,
      required: true,
    },
  },
  methods: {
    updateCriterion() {
      this.$emit('update', this.index);
    },
    isNumberType(typeName) {
      return typeName === 'Amount';
    },
    isTextType(typeName) {
      return typeName === 'Title';
    },
    isDateType(typeName) {
      return typeName === 'Date';
    },
  },
};
</script>
