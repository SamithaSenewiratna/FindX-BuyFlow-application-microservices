import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContextComponent } from './comportents/context/context.component';
import { HomepageComponent } from './comportents/homepage/homepage.component';


const routes: Routes = [
   { path: '', redirectTo: 'context', pathMatch: 'full' },
  {
    path: 'context',
    component: ContextComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomepageComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProcessRoutingModule {}
