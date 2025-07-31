<script setup>
//import HelloWorld from './components/HelloWorld.vue'
//import HomeMenu from './components/HomeMenu.vue';
import { RouterLink } from 'vue-router';
</script>

<template>
  <div class="h-screen flex flex-col">
    <header class="p-3">
      <h1><router-link to="/">Enrollment Manager</router-link></h1>
    </header>
    <main class="p-5 flex-1">
      <router-view />
    </main>
    <footer>
      <small>&copy;Not really copyrighted {{new Date().getFullYear()}}</small>
    </footer>
  </div>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
