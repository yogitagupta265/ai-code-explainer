import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-code-explainer',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './code-explainer.component.html',
  styleUrls : ['./code-explainer.component.css']
})
export class CodeExplainerComponent {
    code ='';
    provider ='mock'
    result=''

    loading =false;
    error =''
    

    constructor(private http:HttpClient) {}

    explain(){

      this.loading =true;
      this.error='';
      this.result='';

      const body = {
        code : this.code
      };

      this.http.post("http://localhost:8080/api/explainCode?provider="+this.provider,body,{responseType:'text'}).subscribe({
        next : res => {
          this.result=res;
          this.loading=false;
        },
        error: err => {
          this.error = "Error in calling API";
          this.loading=false;
        }
      })
    }
}
