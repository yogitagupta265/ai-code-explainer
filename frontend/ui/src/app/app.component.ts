import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CodeExplainerComponent } from './code-explainer/code-explainer.component';


@Component({
  selector: 'app-root',
  standalone:true,
  imports: [CodeExplainerComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // title = 'ui';
}
