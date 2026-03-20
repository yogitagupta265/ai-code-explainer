# AI Code Explainer

Full stack project using Spring Boot + Angular + GenAI API

## Tech Stack

Backend
- Java
- Spring Boot
- Maven
- WebClient

Frontend
- Angular

AI
- HuggingFace Router API
- Public API (for testing)
- Mock provider

## Features

- Explain code using AI
- Multiple provider support (mock / public / hf)
- REST API backend
- Angular UI
- End to end UI → Backend → AI flow
- Real world deployable project
- Provider switch support

## Project Structure

AICodeExplainer
├ backend
├ frontend

## Progress

### Day1
✔ Spring Boot project created  
✔ Controller API working  
✔ GitHub push done

---

### Day2
✔ Fixed project structure issues  
✔ Maven dependencies restored  
✔ IntelliJ module issues resolved  
✔ Package structure corrected  
✔ Service layer created  
✔ WebClient added  
✔ First API integration attempt  
✔ Anthropic API tested (credit error)  
✔ OpenAI API tested (no free tier)  
✔ HuggingFace account created  
✔ Token generated  
✔ Postman API testing started

---

### Day3
✔ Public API integrated (adviceslip)  
✔ Dependency injection issue fixed  
✔ NullPointer error resolved  
✔ Multi-provider service created  
✔ Provider switch logic added  
✔ HuggingFace Router API working  
✔ Model used: meta-llama/Meta-Llama-3-8B-Instruct  
✔ Postman tests successful  
✔ Mock / Public / HF all working  
✔ Removed secrets from code  
✔ GitHub push protection issue fixed  
✔ README updated

---

### Day4
✔ Angular project created  
✔ Angular CLI configured  
✔ CodeExplainer component created  
✔ UI added (textbox + dropdown + button + result)  
✔ Standalone component setup (Angular 19)  
✔ HttpClient provider configured using app.config.ts  
✔ Fixed ngModel error  
✔ Fixed HttpClient injection error  
✔ Fixed CORS issue in Spring Boot  
✔ Connected Angular → Spring Boot API  
✔ Tested API from UI  
✔ Mock provider working from UI  
✔ Public API working from UI  
✔ HuggingFace API working from UI  
✔ Full end-to-end flow completed

---

## API

POST /api/explain

Body
{
"code": "int a = 5;"
}


Optional param
/api/explain?provider=mock
/api/explain?provider=public
/api/explain?provider=hf


---

## Providers

Mock  
Returns static response

Public API  
https://api.adviceslip.com

HuggingFace Router  
https://router.huggingface.co/v1/chat/completions

Model used  
meta-llama/Meta-Llama-3-8B-Instruct

---

## Notes

Secrets will be moved to application.properties in future.
Project created for learning GenAI integration in Spring Boot.

## Status

Project in progress  
Goal: Full stack GenAI demo project with multiple providers