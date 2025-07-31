<script setup>
import { onMounted, ref } from 'vue';

const api = import.meta.env.VITE_API_URL;
const data = ref([]);
const search = ref('');

onMounted(()=>{
    getCourses();
});
const getCourses = ()=>{
    fetch(`${api}/courses?search=${encodeURI(search.value)}`).then(async (res)=>{
        console.log(res);
        if (res.ok){
            data.value = await res.json();
            console.log(data.value);
        }
    })
}
const deleteCourse = async (id)=>{
    console.log(`delete ${id}`);
    fetch(`${api}/courses/${id}`,
        {
            method:'DELETE'
        }
    ).then(async (res)=>{
        if (res.ok){
            getCourses();
        }
    })
}
</script>
<template>
    <section>
        <h2 class="text-lg font-bold">Courses</h2>
        <router-link class="inline-block py-1 px-2 border rounded-sm" to="/course/">Add Course</router-link>
        <div class="flex justify-end flex-row gap-4">
            <input type="search" class="border w-50" id="search" name="search" v-model="search" placeholder="Search by subject" />
            <button type="button" @click="getCourses">Search</button>
        </div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>Subject</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Teacher</th>
                    <th>Students</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="course in data" :key="course.id">
                    <td>{{course.subject.name}}</td>
                    <td>{{new Date(course.startDate).toLocaleDateString()}}</td>
                    <td>{{new Date(course.endDate).toLocaleDateString()}}</td>
                    <td>{{course.teacher?.name}}</td>
                    <td>{{course.studentCourses.length}}</td>
                    <td>
                        <router-link :to="`/course/${course.id}`">View/Edit</router-link>
                        <button @click="deleteCourse(course.id)" class="bg-red-700 text-white inline-block ml-2">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
</template>
<style scoped>
</style>