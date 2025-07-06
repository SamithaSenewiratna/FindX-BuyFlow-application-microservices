import { Routes } from '@angular/router';
import { ContextComponent } from './modules/process/components/context/context.component';
import { HomepageComponent } from './modules/process/components/homepage/homepage.component';
import { NotFoundError } from 'rxjs';


export const routes: Routes = [


//      {path: '', redirectTo: '/context', pathMatch: 'full'},
//   {
//     path: 'context', component: ContextComponent,
//     children: [
//       {path:'',redirectTo:'/context/home',pathMatch:'full'},
//       { path: 'home', component: HomepageComponent }
//     ]
//   }

{path:'',redirectTo:'/process',pathMatch:'full'},
{
    path:'process',loadChildren:
    ()=>import('./modules/process/process.module').then(e=>e.ProcessModule)
},
{
    path:'security',loadChildren:
    ()=>import('./modules/security/security.module').then(e=>e.SecurityModule)
},
{
    path:'dashboard',loadChildren:
    ()=>import('./modules/dashboard/dashboard.module').then(e=>e.DashboardModule)
}

];
