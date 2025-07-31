<script setup>
import { onMounted, ref } from 'vue';

const api = import.meta.env.VITE_API_URL;
const data = ref([]);

onMounted(()=>{
    getStudents();
});
const getStudents = ()=>{
    fetch(`${api}/students`).then(async (res)=>{
        console.log(res);
        if (res.ok){
            data.value = await res.json();
            console.log(data.value);
        }
    })

}
const deleteStudent = async (id)=>{
    console.log(`delete ${id}`);
    fetch(`${api}/students/${id}`,
        {
            method:'DELETE'
        }
    ).then(async (res)=>{
        if (res.ok){
            getStudents();
        }
    })
}
</script>
<template>
    <section>
        <h2 class="text-lg font-bold">Students</h2>
        <router-link class="inline-block py-1 px-2 border rounded-sm" to="/student/">Add Student</router-link>
        <table class="data-table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Courses</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="student in data" :key="student.id">
                    <td>{{student.name}}</td>
                    <td>{{student.studentCourses.length}}</td>
                    <td>
                        <router-link :to="`/student/${student.id}`">View/Edit</router-link>
                        <button @click="deleteStudent(student.id)" class="bg-red-700 text-white inline-block ml-2">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
</template>
<style scoped>
</style>