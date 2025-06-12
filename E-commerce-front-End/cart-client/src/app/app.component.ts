import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ContextComponent } from './modules/process/comportents/context/context.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ContextComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'cart-client';
}
