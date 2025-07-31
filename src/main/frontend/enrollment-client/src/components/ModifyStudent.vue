<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const id = route.params.id;
const data = ref({});
const api = import.meta.env.VITE_API_URL;

const future_courses = ref([]);
const enrollment_choice = ref(null);
const enroll_message = ref('');

const message = ref('');

console.log('id', id);

onMounted(async () => {
    await getEnrollmentCourses();
    if (id != null) {
        getStudent(id);
    }
});
const getEnrollmentCourses = async ()=>{
    fetch(`${api}/courses/future/${id}`).then(async (res) => {
        if (res.ok) {
            const res_data = await res.json();
            console.log(res_data);
            future_courses.value = res_data;
        }
    })
}
const getStudent = () => {
    fetch(`${api}/students/${id}`).then(async (res) => {
        if (res.ok) {
            data.value = await res.json();
            console.log(data.value);
        }
    })
}
const saveStudent = (e) => {
    e.preventDefault();
    if (!data.value.name){
        message.value = "Name is required";
    }else{
        fetch(`${api}/students${id != null ? `/${id}` : ''}`,
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
                router.push({ path: `/students` });
            }
        })
    }

}
const enroll_course = (e) => {
    e.preventDefault();
    console.log(enrollment_choice.value);
    console.log(JSON.parse(enrollment_choice.value));
    const {courseId, subjectId, startDate, endDate} = JSON.parse(enrollment_choice.value);
    console.log(courseId, subjectId, startDate, endDate);
    console.log(data.value.studentCourses);
    let overlap = data.value.studentCourses.filter(sc=>sc.course.subject.id==subjectId &&
        new Date(sc.course.startDate).toISOString().split('T')[0]<=endDate &&
        new Date(sc.course.endDate).toISOString().split('T')[0]>=startDate
    );
    console.log(overlap);
    if (overlap.length == 0){
        fetch(`${api}/students/${id}/${courseId}`,
            {
                method:'POST',
                headers:{
                    'Content-Type':'application/json'
                }

            }
        ).then(async (res)=>{
            if (res.ok){
                const res_data = await res.json();
                await Promise.all([getStudent(), getEnrollmentCourses()]);
                enroll_message.value = "Course enrolled";
            }
        });

    }else{
        enroll_message.value = "You can't enroll because there is an overlap";
    }
}
const unenroll_course = (courseId) => {


    fetch(`${api}/students/${id}/${courseId}`,
        {
            method:'DELETE',
            headers:{
                'Content-Type':'application/json'
            }
        }
    ).then(async (res)=>{
        if (res.ok){
            const res_data = await res.json();
            await Promise.all([getStudent(), getEnrollmentCourses()]);
            enroll_message.value = "Course unenrolled";
        }
    });


}
</script>
<template>
    <form @submit="saveStudent">
        <h2 class="text-lg font-bold">{{id ? 'Edit':'Add'}} Student</h2>
        <div v-if="message" class="text-red-500">{{ message }}</div>
        <div class="py-5">
            <label for="name" class="inline-block w-20 pr-3 text-right">Name</label>
            <input type="text" class="border" id="name" name="name" v-model="data.name" />
        </div>
        <div class="flex flex-row justify-center items-center gap-4">
            <router-link class="border inline-block rounded-lg py-[.2em] px-4" to="/students">Back</router-link>
            <button type="submit">Save</button>
        </div>
        <section v-if="id" class="mt-5">
            <h3 class="text-lg">Course Enrollment</h3>
            <div class="py-4 flex flex-row justify-center items-center gap-3">
                <label for="enroll" class="inline-block w-30 text-right">Select Course</label>
                <select class="border w-40" id="enroll" name="enroll" v-model="enrollment_choice">
                    <option v-for="course in future_courses" :key="course.id"
                    :value="`{&quot;courseId&quot;:${course.id}, &quot;subjectId&quot;:${course.subject.id},
                    &quot;startDate&quot;:&quot;${new Date(course.startDate).toISOString().split('T')[0]}&quot;,
                    &quot;endDate&quot;:&quot;${new Date(course.endDate).toISOString().split('T')[0]}&quot;}`">
                        {{ `${course.subject.name} (${new Date(course.startDate).toLocaleDateString()}-${new Date(course.endDate).toLocaleDateString()})` }}
                    </option>
                </select>
                <button @click="enroll_course">Enroll</button>
            </div>
            <div v-if="enroll_message">{{enroll_message}}</div>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Grade Point</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="sc in data.studentCourses" :key="sc.course.id">
                        <td>{{sc.course.subject.name}}</td>
                        <td>{{new Date(sc.course.startDate).toLocaleDateString()}}</td>
                        <td>{{new Date(sc.course.endDate).toLocaleDateString()}}</td>
                        <td>{{sc.grade}}</td>
                        <td>
                            <button v-if="sc.course.endDate > Date.now()" type="button" class="bg-red-700 text-white inline-block ml-2" @click="unenroll_course(sc.course.id)">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>

    </form>
</template>
<style scoped>
</style>