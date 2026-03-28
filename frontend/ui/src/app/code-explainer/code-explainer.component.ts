import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { marked } from 'marked';

interface APIResponse{
  status:string;
  data:any;
  message:string;
}
@Component({
  selector: 'app-code-explainer',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './code-explainer.component.html',
  styleUrls : ['./code-explainer.component.css']
})

export class CodeExplainerComponent {
    code ='';
    selectedProvider ='mock'
    result=''

    loading =false;
    error =''
    
    providers=[
      {label:'Mock' ,value:'mock'},
      {label:'Public API', value:'public'},
      {label:'HuggingFace AI',value:'hf'}
    ];

    
    constructor(private http:HttpClient) {}

    explain(){

      this.loading =true;
      this.error='';
      this.result='';

      const body = {
        code : this.code
      };

      this.http.post<APIResponse>("http://localhost:8080/api/explainCode?provider="+this.selectedProvider,body,
        //removed as we wanted to give a error box when empty input is given 
        //{responseType:'text'}
      ).subscribe({
        next : (res : APIResponse) => {

          if(res.status === 'FAILED'){
            this.error =res.message;
            this.result='';
          }else{
            /*
            this.result=res.data?.result || res.data;
            */
           this.result = marked.parse(res.data?.result || res.data) as string;
            this.error='';
          }
          this.loading=false; 
        },
        error: err => {
          this.error = "Something went wrong. Please try again.";
          this.loading=false;
        }
      })
    }
}
