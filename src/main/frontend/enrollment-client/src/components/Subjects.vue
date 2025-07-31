<script setup>
import { onMounted, ref } from 'vue';

const api = import.meta.env.VITE_API_URL;
const data = ref([]);

onMounted(()=>{
    getSubjects();
});
const getSubjects = ()=>{
    fetch(`${api}/subjects`).then(async (res)=>{
        console.log(res);
        if (res.ok){
            data.value = await res.json();
            console.log(data.value);
        }
    })
}
const deleteSubject = async (id)=>{
    console.log(`delete ${id}`);
    fetch(`${api}/subjects/${id}`,
        {
            method:'DELETE'
        }
    ).then(async (res)=>{
        if (res.ok){
            getSubjects();
        }
    })
}

</script>
<template>
    <section>
        <h2 class="text-lg font-bold">Subjects</h2>
        <router-link class="inline-block py-1 px-2 border rounded-sm" to="/subject/">Add Subject</router-link>
        <table class="data-table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Courses</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="subject in data" :key="subject.id">
                    <td>{{subject.name}}</td>
                    <td>{{subject.courses.length}}</td>
                    <td>
                        <router-link :to="`/subject/${subject.id}`">View/Edit</router-link>
                        <button @click="deleteSubject(subject.id)" class="bg-red-700 text-white inline-block ml-2">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
</template>
<style scoped>

</style>