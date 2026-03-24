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
- Standard API response wrapper
- Global exception handling
- Secure config using application.properties
- DTO based architecture
- Production style backend structure

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

---

### Day5
✔ UI improved (clean layout, centered UI, better spacing)  
✔ Result box styling added (dark theme, formatted output, scroll support)  
✔ Fixed long text overflow issue in result  
✔ Loader added while API is processing  
✔ Button disabled during API call  
✔ Error handling UI added  
✔ Parsed HuggingFace JSON response (return only AI message)  
✔ Backend response cleaned before sending to UI  
✔ Moved API key to application.properties  
✔ Used @Value to inject config securely  
✔ Removed secrets from source code  
✔ Added application.properties to .gitignore  
✔ Removed properties file from git cache  
✔ Project now follows secure config practice

---

### Day6
✔ Added DTO layer (ExplainRequest / ExplainResponse)  
✔ Introduced ApiResponse<T> wrapper for standard API response  
✔ Implemented Global Exception Handler using @RestControllerAdvice  
✔ Added ErrorResponse structure for failures  
✔ Added validation for provider in service layer  
✔ Throwing RuntimeException for invalid provider  
✔ Standardized API success / error format  
✔ Refactored controller to use DTO instead of raw String  
✔ Backend now follows real-world Spring Boot architecture  
✔ Improved project structure (dto / exception / service separation)

---

## API

POST /api/explainCode

Body

{
"code": "int a = 5;"
}

Optional param

/api/explainCode?provider=mock  
/api/explainCode?provider=public  
/api/explainCode?provider=hf

Response format (Success)

{
"status": "SUCCESS",
"data": {
"result": "...",
"provider": "hf"
},
"message": null
}

Response format (Error)

{
"status": "FAILED",
"data": null,
"message": "Invalid provider"
}

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

Secrets stored in application.properties (not committed to git)  
Global exception handling implemented  
Standard API response format used  
DTO + Service + Exception architecture followed  
Project created to demonstrate real-world Spring Boot + Angular + GenAI integration

## Architecture

Controller → Service → Provider → External API  
DTO used for request/response  
ApiResponse used for standard output  
GlobalExceptionHandler for error handling  
application.properties for secrets

## Status

Project in progress  
Goal: Full stack GenAI demo project with clean UI, secure config, and multi-provider AI integration

