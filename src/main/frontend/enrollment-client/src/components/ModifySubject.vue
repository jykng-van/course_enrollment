<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const id = route.params.id;
const data = ref({});
const api = import.meta.env.VITE_API_URL;

const message = ref('');

console.log('id', id);

onMounted(() => {
    if (id != null) {
        getSubject(id);
    }
});
const getSubject = () => {
    fetch(`${api}/subjects/${id}`).then(async (res) => {
        if (res.ok) {
            data.value = await res.json();
            console.log(data.value);
        }
    })
}
const saveSubject = (e) => {
    e.preventDefault();
    if (!data.value.name){
        message.value = 'Name is required';
    }else{
        fetch(`${api}/subjects${id != null ? `/${id}` : ''}`,
            {
                method: id == null ? 'POST' : 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data.value)
            }
        ).then(async (res) => {
            if (res.ok) {
                const res_data = await res.json();
                data.value = res_data;
                console.log(data.value);
                router.push({ path: `/subjects` });
            }
        })
    }

}

</script>
<template>
    <form @submit="saveSubject">
        <h2 class="text-lg font-bold">{{id ? 'Edit':'Add'}} Subject</h2>
        <div v-if="message" class="text-red-500">{{ message }}</div>
        <div class="py-5">
            <label for="name" class="inline-block w-20 pr-3 text-right">Name</label>
            <input type="text" class="border" id="name" name="name" v-model="data.name" />
        </div>
        <div class="flex flex-row justify-center items-center gap-4">
            <router-link class="border inline-block rounded-lg py-[.2em] px-4" to="/subjects">Back</router-link>
            <button type="submit">Save</button>
        </div>
    </form>
</template>
<style scoped>
</style>