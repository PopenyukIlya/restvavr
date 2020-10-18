var vavrRestApi=Vue.resource('/restvavr{/id}');

function getIndex(list,id){
for (var i=0;i<list.length;i++){
    if (list[i].id===id){
        return i;
    }
    return -1;
}
}

Vue.component('user-form',{
    props:['users','userAttr'],
    data: function(){
        return{
            name:'',
            id:''
        }
    },
    watch:{
        userAttr: function(newVal,oldVal){
           this.name=newVal.name;
           this.id=newVal.id;
        }
    },
    template:'<div>' +
        '<input type="text" placeholder="Username" v-model="name" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods:{
        save:function () {
            var user={name: this.name};
            if (this.id){
                vavrRestApi.update({id:this.id},user).then(result=>
                result.json().then(data=>{
                    var index=getIndex(this.users,data.id);
                    this.users.splice(index, 1, data);
                    this.name='';
                    this.id=''
                })
                )
            }else {
            vavrRestApi.save({},user).then(result=>
                result.json().then(data=>{
                    this.users.push(data);
                    this.name=''
                })
            )
        }}
    }
});

Vue.component('user-row',{
    props: ['user','editMethod','users'],
    template:'<div>' +
        '<i>{{ user.id }} </i>{{user.name}}' +
        '<span>' +
        '</span>' +
        '<input type="button" value="Edit" @click="edit">'+
        '<input type="button" value="X" @click="del">'+
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.user)
        },
        del:function () {
            vavrRestApi.remove({id:this.user.id}).then(result=>{
                if (result.ok){
                    this.users.splice(this.users.indexOf(this.user),1)
                }
            })
        }
    }
});

Vue.component('users-list',{
    props:['users'],
    data:function(){
        return{
            user:null
        }
    },
    template:'' +
        '<div>' +
        '<user-form :users="users" :userAttr="user"/>'+
        '<user-row v-for="user in users" :key="user.id" :user="user" ' +
        ':editMethod="editMethod" :users="users"/>' +
        '</div>',
    created: function () {
        vavrRestApi.get().then(result=>result.json().then(
            data=>data.orNull.forEach(orNull=>this.users.push(orNull)))
    )
    },
    methods:{
        editMethod:function (user) {
            this.user=user;
        }
    }
});


var app = new Vue({
    el: '#app',
    template: '<users-list :users="users"/>',
    data: {
        users: []
    }
});