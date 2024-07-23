<template>
  <button
      :class="[
      'px-4 py-2 rounded-lg font-semibold focus:outline-none',
      buttonClasses,
      stateClasses
    ]"
      :disabled="disabled"
  >
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: 'AppButton',
  props: {
    color: {
      type: String,
      default: 'blue',
      validator(value) {
        return ['blue', 'red-outline', 'gray', 'green', 'gray-outline'].includes(value);
      }
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    buttonClasses() {
      switch (this.color) {
        case 'red-outline':
          return 'text-red-500 border border-red-500';
        case 'gray':
          return 'bg-gray-500 text-white';
        case 'green':
          return 'bg-green-500 text-white';
        case 'gray-outline':
          return 'text-gray-400 border border-gray-400';
        default:
          return 'bg-blue-500 text-white';
      }
    },
    stateClasses() {
      if (this.disabled) {
        return 'opacity-50 cursor-not-allowed';
      }
      switch (this.color) {
        case 'red-outline':
          return 'hover:bg-red-500 hover:text-white active:bg-red-600';
        case 'gray':
          return 'hover:bg-gray-600 active:bg-gray-700';
        case 'green':
          return 'hover:bg-green-600 active:bg-green-700';
        case 'gray-outline':
          return 'hover:text-gray-500 border hover:border-gray-500 active:border-gray-600 active:text-gray-600' ;
        default:
          return 'hover:bg-blue-600 active:bg-blue-700';
      }
    }
  },
};
</script>


<style scoped>
button:disabled {
  cursor: not-allowed;
}
</style>
