<script setup>
import { onMounted, ref } from 'vue';

const api = import.meta.env.VITE_API_URL;
const data = ref([]);

onMounted(()=>{
    getTeachers();
});
const getTeachers = ()=>{
    fetch(`${api}/teachers`).then(async (res)=>{
        console.log(res);
        if (res.ok){
            data.value = await res.json();
            console.log(data.value);
        }
    })

}
const deleteTeacher = async (id)=>{
    console.log(`delete ${id}`);
    fetch(`${api}/teachers/${id}`,
        {
            method:'DELETE'
        }
    ).then(async (res)=>{
        if (res.ok){
            getTeachers();
        }
    })
}

</script>
<template>
    <section>
        <h2 class="text-lg font-bold">Teachers</h2>
        <router-link class="inline-block py-1 px-2 border rounded-sm" to="/teacher/">Add Teacher</router-link>
        <table class="data-table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Courses Taught/Teaching</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="teacher in data" :key="teacher.id">
                    <td>{{teacher.name}}</td>
                    <td>{{teacher.courses.length}}</td>
                    <td>
                        <router-link :to="`/teacher/${teacher.id}`">View/Edit</router-link>
                        <button @click="deleteTeacher(teacher.id)" class="bg-red-700 text-white inline-block ml-2">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
</template>
<style scoped>

</style>