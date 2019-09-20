import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../model/task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = 'http://localhost:8000/projectmanagement/api/task';

  constructor(private http: HttpClient) {
  }

  addTask(task: Task): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, task);
  }

  getAllTasks(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/all`);
  }

  getTaskByProject(task: Task): Observable<any> {
    return this.http.post(`${this.baseUrl}/projecttask`, task);
  }


  updateTask(task: Task): Observable<any> {
    return this.http.put(`${this.baseUrl}/update`, task);
  }

  endTask(task: Task): Observable<any> {
    return this.http.put(`${this.baseUrl}/complete`, task);
  }
}
