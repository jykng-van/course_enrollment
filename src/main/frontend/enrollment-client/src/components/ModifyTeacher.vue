<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const id = route.params.id;
const data = ref({});
const api = import.meta.env.VITE_API_URL;

const message = ref('');

console.log('id',id);

onMounted(()=>{
    if (id != null){
        getTeacher(id);
    }
});
const getTeacher = ()=>{
    fetch(`${api}/teachers/${id}`).then(async (res)=>{
        if (res.ok){
            data.value = await res.json();
            console.log(data.value);
        }
    })
}
const saveTeacher = (e)=>{
    e.preventDefault();
    if (!data.value.name){
        message.value = 'Name is required';
    }else{
        fetch(`${api}/teachers${id!=null ? `/${id}`:''}`,
            {
                method: id==null ? 'POST':'PUT',
                headers:{
                    'Content-Type':'application/json'
                },
                body:JSON.stringify(data.value)
            }
        ).then(async (res)=>{
            if (res.ok){
                const res_data = await res.json();
                data.value = res_data;
                console.log(data.value);
                router.push({path:`/teachers`});
            }
        })
    }

}

</script>
<template>
    <form @submit="saveTeacher">
        <h2 class="text-lg font-bold">{{id ? 'Edit':'Add'}} Teacher</h2>
        <div v-if="message" class="text-red-500">{{ message }}</div>
        <div class="py-5">
            <label for="name" class="inline-block w-20 pr-3 text-right">Name</label>
            <input type="text" class="border" id="name" name="name" v-model="data.name" />
        </div>
        <div class="flex flex-row justify-center items-center gap-4">
            <router-link class="border inline-block rounded-lg py-[.2em] px-4" to="/teachers">Back</router-link>
            <button type="submit">Save</button>
        </div>

        <section v-if="id" class="mt-5">
            <h3 class="text-lg">Courses</h3>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="c in data.courses" :key="c.id">
                        <td>{{c.subject.name}}</td>
                        <td>{{new Date(c.startDate).toLocaleDateString()}}</td>
                        <td>{{new Date(c.endDate).toLocaleDateString()}}</td>
                    </tr>
                </tbody>
            </table>
        </section>
    </form>
</template>
<style scoped>
</style>