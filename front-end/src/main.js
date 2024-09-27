import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import Button from 'primevue/button';
import ColorPicker from 'primevue/colorpicker';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'primeicons/primeicons.css';
import 'primevue/resources/themes/saga-blue//theme.css';
const app = createApp(App);
app.use(PrimeVue);
app.component('Button', Button);
app.component('ColorPicker',ColorPicker)
app.mount('#app')
