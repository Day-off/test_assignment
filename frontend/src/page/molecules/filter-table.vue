<template>
  <table class="min-w-full divide-y divide-gray-200">
    <thead>
      <tr>
        <HeaderCell>ID</HeaderCell>
        <HeaderCell>Name</HeaderCell>
        <HeaderCell>Criteria</HeaderCell>
        <HeaderCell>Actions</HeaderCell>
      </tr>
    </thead>
    <tbody class="bg-white divide-y divide-gray-200">
      <tr v-for="filter in filters" :key="filter.id">
        <BodyCell>{{ filter.id }}</BodyCell>
        <BodyCell>{{ filter.name }}</BodyCell>
        <BodyCell>
          <button
            @click="toggleDropdown(filter.id)"
            class="text-blue-600 hover:text-blue-800"
          >
            {{ isDropdownOpen(filter.id) ? 'Hide Criteria' : 'Show Criteria' }}
          </button>
          <div
            v-if="isDropdownOpen(filter.id)"
            class="dropdown-content mt-2 p-4 bg-gray-100 border rounded-lg"
          >
            <ul>
              <li v-if="filter.criteria.length === 0">No criteria</li>
              <li
                v-else
                v-for="criterion in filter.criteria"
                :key="criterion.id"
              >
                {{
                  criterion.criterionType.name +
                  ' ' +
                  criterion.comparingConditionType.conditionName
                }}:
                {{ criterion.conditionValue }}
              </li>
            </ul>
          </div>
        </BodyCell>
        <BodyCell>
          <button
            @click="$emit('edit-filter', filter)"
            class="text-blue-600 hover:text-blue-800 pr-3"
          >
            Edit
          </button>
          <button
            @click="$emit('delete-filter', filter.id)"
            class="text-red-600 hover:text-red-800"
          >
            Delete
          </button>
        </BodyCell>
      </tr>
    </tbody>
  </table>
</template>

<script>
import HeaderCell from '@/ui/table/header-cell.vue';
import BodyCell from '@/ui/table/body-cell.vue';

export default {
  name: 'FilterTable',
  components: {
    HeaderCell,
    BodyCell,
  },
  props: {
    filters: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      openDropdownId: null,
    };
  },
  methods: {
    toggleDropdown(filterId) {
      this.openDropdownId = this.openDropdownId === filterId ? null : filterId;
    },
    isDropdownOpen(filterId) {
      return this.openDropdownId === filterId;
    },
  },
};
</script>

<style scoped>
.dropdown-content {
  max-height: 200px;
  overflow-y: auto;
}
</style>
