<template>
  <div class="flex justify-end items-center space-x-2 pt-2">
    <div class="pr-5">
      <AppButton
        color="gray"
        @click="changePage(currentPage - 1)"
        :disabled="currentPage === 1"
      >
        Previous
      </AppButton>
      <span class="text-gray-600 font-semibold pr-3 pl-3"
        >{{ currentPage }} / {{ totalPages }}</span
      >
      <AppButton
        color="gray"
        @click="changePage(currentPage + 1)"
        :disabled="currentPage === totalPages"
      >
        Next
      </AppButton>
    </div>
    <label for="pageSize" class="ml-4 text-gray-600 font-semibold"
      >Page Size:</label
    >
    <select
      v-model="localPageSize"
      @change="onPageSizeChange"
      class="border border-gray-300 rounded-lg p-2 ml-2 focus:outline-none focus:border-blue-500"
    >
      <option v-for="size in pageSizeOptions" :key="size" :value="size">
        {{ size }}
      </option>
    </select>
  </div>
</template>

<script>
import AppButton from '@/ui/button/app-button.vue';

export default {
  name: 'Pagination',
  components: {
    AppButton,
  },
  props: {
    total: {
      type: Number,
      required: true,
    },
    currentPage: {
      type: Number,
      required: true,
    },
    pageSize: {
      type: Number,
      required: true,
    },
    pageSizeOptions: {
      type: Array,
      required: true,
      default: () => [5, 10, 20, 50],
    },
  },
  data() {
    return {
      localPageSize: this.pageSize,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.total / this.localPageSize);
    },
  },
  methods: {
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.$emit('page-changed', page);
      }
    },
    onPageSizeChange() {
      this.$emit('page-size-changed', this.localPageSize);
    },
  },
};
</script>
