<script setup>
import { RouterLink } from 'vue-router';
</script>
<template>
    <nav class="grid grid-cols-2 grid-rows-2">
        <router-link to="/teachers" class="border font-bold text-2xl text-white bg-purple-400 h-50 flex items-center justify-center">Teachers</router-link>
        <router-link to="/students" class="border font-bold text-2xl text-white text-white bg-blue-400 h-50 flex items-center justify-center">Students</router-link>
        <router-link to="/courses" class="border font-bold text-2xl text-white text-white bg-green-400 h-50 flex items-center justify-center">Courses</router-link>
        <router-link to="/subjects" class="border font-bold text-2xl text-white text-white bg-orange-400 h-50 flex items-center justify-center">Subjects</router-link>
    </nav>
</template>

<style scoped>
</style>