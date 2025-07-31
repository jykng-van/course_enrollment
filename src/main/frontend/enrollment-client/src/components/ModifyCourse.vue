<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const id = route.params.id;
const data = ref({});
const api = import.meta.env.VITE_API_URL;

const message = ref('');

const subjects = ref([]);
const selected_subject = ref(null);

const teachers = ref([]);
const selected_teacher = ref(null);

const start_date = ref(null);
const end_date = ref(null);

console.log('id', id);

onMounted(async () => {
    await Promise.all([getSubjects(), getTeachers()]);

    if (id != null) {
        getCourse(id);
    }
});
const getSubjects = async () => {
    await fetch(`${api}/subjects`).then(async (res) => {
        if (res.ok) {
            const res_data = await res.json();
            subjects.value = res_data.map(s=>{
                return {id:s.id, name:s.name}
            });
            console.log(res_data);

        }
    })
}
const getTeachers = async () => {
    await fetch(`${api}/teachers`).then(async (res) => {
        if (res.ok) {
            const res_data = await res.json();
            teachers.value = res_data.map(t=>{
                return {id:t.id, name:t.name}
            });
            console.log(res_data);

        }
    })
}
const getCourse = () => {
    fetch(`${api}/courses/${id}`).then(async (res) => {
        if (res.ok) {
            const res_data = await res.json();
            data.value =res_data;
            console.log(res_data);

            //select subject
            selected_subject.value = res_data.subject.id;
            //select teacher
            if (res_data.teacher){
                selected_teacher.value = res_data.teacher.id;
            }


            //set dates
            let date = new Date( res_data.startDate);
            console.log('start date', res_data.startDate, date);
            start_date.value = date.toISOString().split('T')[0]; //to get yyyy-mm-dd format

            date = new Date( res_data.endDate);
            console.log('end date', res_data.endDate, date);
            end_date.value = date.toISOString().split('T')[0]; //to get yyyy-mm-dd format
        }
    })
}
const saveCourse = (e) => {
    e.preventDefault();
    //some validation
    console.log('saveCourse validate', selected_subject.value, start_date.value, end_date.value);
    if (!selected_subject.value == null){
        message.value="Subject is required";
    }else if (!start_date.value){
        message.value="Start Date is required";
    }else if (!end_date.value){
        message.value="End Date is required";
    }else if (start_date.value >= end_date.value){
        message.value="Start Date must be less than End Date"
    }else{
        //prepare course because it's special
        const course = {
            subjectId:selected_subject.value,
            teacherId:selected_teacher.value,
            startDate:start_date.value,
            endDate:end_date.value,
        }
        console.log(course);
        fetch(`${api}/courses${id != null ? `/${id}` : ''}`,
            {
                method: id == null ? 'POST' : 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(course)
            }
        ).then(async (res) => {
            if (res.ok) {
                const res_data = await res.json();
                data.value = res_data;
                console.log(data.value);
                router.push({ path: `/courses` });
            }
        })
    }

}

</script>
<template>
    <form @submit="saveCourse">
        <h2 class="text-lg font-bold">{{id ? 'Edit':'Add'}} Course</h2>
        <div v-if="message" class="text-red-500">{{ message }}</div>
        <div class="py-5">
            <label for="subject" class="inline-block w-30 pr-3 text-right">Subject</label>
            <select class="border w-40" id="subject" name="subject" v-model="selected_subject">
                <option v-for="subject in subjects" :key="subject.id" :value="subject.id">{{ subject.name }}</option>
            </select>
        </div>
        <div class="py-5">
            <label for="teacher" class="inline-block w-30 pr-3 text-right">Teacher</label>
            <select class="border w-40" id="teacher" name="teacher" v-model="selected_teacher">
                <option value=""></option>
                <option v-for="teacher in teachers" :key="teacher.id" :value="teacher.id">{{ teacher.name }}</option>
            </select>
        </div>
        <div class="py-5">
            <label for="start_date" class="inline-block w-30 pr-3 text-right">Start Date</label>
            <input type="date" class="border w-40" id="start_date" name="start_date" v-model="start_date" />
        </div>
        <div class="py-5">
            <label for="end_date" class="inline-block w-30 pr-3 text-right">End Date</label>
            <input type="date" class="border w-40" id="end_date" name="end_date" v-model="end_date" />
        </div>
        <div class="flex flex-row justify-center items-center gap-4">
            <router-link to="/courses">Back</router-link>
            <button type="submit">Save</button>
        </div>

    </form>
</template>
<style scoped>
</style>