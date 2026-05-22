import { TestService } from '@/app/core/services/test';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './test.html',
  styleUrl: './test.css',
})
export class TestComponent implements OnInit {
  private testService = inject(TestService)
  private cdr = inject(ChangeDetectorRef)

  message = ''
  ngOnInit(): void {
    console.log('TestComponent ngOnInit called');
    this.testService.testApi().subscribe({
      next: (res) => {
        console.log('Received API response:', res);
        this.message = res
        this.cdr.detectChanges()
      },
      error: (err) => {
        console.error('API Error:', err);
      }
    })
  }

}
