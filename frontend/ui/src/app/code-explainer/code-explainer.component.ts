import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-code-explainer',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './code-explainer.component.html',
  styleUrls : ['./code-explainer.component.css']
})
export class CodeExplainerComponent {
    code ='';
    provider ='mock'
    result=''

    constructor(private http:HttpClient) {}

    explain(){
      const body = {
        code : this.code
      };

      this.http.post("http://localhost:8080/api/explainCode?provider="+this.provider,body,{responseType:'text'}).subscribe(res => {
        this.result = res;
      })
    }
}
