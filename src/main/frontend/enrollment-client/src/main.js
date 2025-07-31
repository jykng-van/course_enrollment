import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createMemoryHistory, createRouter } from 'vue-router'
import HomeMenu from './components/HomeMenu.vue'
import Teachers from './components/Teachers.vue'
import Students from './components/Students.vue'
import Courses from './components/Courses.vue'
import Subjects from './components/Subjects.vue'
import ModifyTeacher from './components/ModifyTeacher.vue'
import ModifySubject from './components/ModifySubject.vue'
import ModifyStudent from './components/ModifyStudent.vue'
import ModifyCourse from './components/ModifyCourse.vue'

const routes =
    [
        //home page
        {path:'/',name:'home',component:HomeMenu,},
        //main pages
        {path:'/teachers',name:'teachers',component:Teachers,},
        {path:'/students',name:'students',component:Students,},
        {path:'/courses',name:'courses',component:Courses,},
        {path:'/subjects',name:'subjects',component:Subjects,},
        //edit pages
        {path:'/teacher/',name:'new teacher',component:ModifyTeacher},
        {path:'/teacher/:id',name:'edit teacher',component:ModifyTeacher},
        {path:'/subject/',name:'new subject', component:ModifySubject},
        {path:'/subject/:id',name:'edit subject', component:ModifySubject},
        {path:'/student/',name:'new student', component:ModifyStudent},
        {path:'/student/:id',name:'edit student', component:ModifyStudent},
        {path:'/course/',name:'new course', component:ModifyCourse},
        {path:'/course/:id',name:'edit course', component:ModifyCourse},
    ];

const router = createRouter({
  history: createMemoryHistory(),
  routes,
})

createApp(App).use(router).mount('#app')
