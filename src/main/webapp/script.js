new Vue({
  el: '#app',
  data() {
    return {
      list: [],
      new_task: '',
      show: false,
    };
  },
  mounted() {
    this.getTodos();
  },
  computed:{
    incompleted: function() {
      return this.list.filter(function(item) {
        return !item.done;
      })
    },
    completed: function() {
      return this.list.filter(function(item) {
        return item.done;
      }); 
    }
  },
  methods: {
    getTodos() {
      if (localStorage.getItem('todo_list')) {
        this.list = JSON.parse(localStorage.getItem('todo_list'));
      }
    },
    addItem() {
      if (this.new_task) {
        this.list.unshift({
          id: this.list.length,
          title: this.new_task,
          done: false,
        });
      }
      this.new_task = '';
      return true;
    },
    deleteItem(item) {
      this.list.splice(this.list.indexOf(item), 1);
    },
    toggleShowComplete() {
      this.show = !this.show;
    },
    clearAll() {
      this.list = [];
    }
  },
  watch: {
    list: {
      handler: function(updated_list) {
        localStorage.setItem('todo_list', JSON.stringify(updated_list));
      },
      deep: true
    }
  },
});