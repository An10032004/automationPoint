import { Routes } from '@angular/router';
import { TestComponent } from './features/test/test';

export const routes: Routes = [
    {
        path: '',
        component: TestComponent
    },
    {
        path: 'user/test',
        component: TestComponent
    }
];  
