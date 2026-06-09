import { Essay } from '@/app/core/models/essay.model';
import { EssayService } from '@/app/core/services/essay.service';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-essay',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './essay.html',
  styleUrl: './essay.css',
})
export class EssayComponent implements OnInit {
  private essayService = inject(EssayService);
  private cdr = inject(ChangeDetectorRef)
  essays: Essay[] = []
  essay?: Essay
  formData: Essay = {
    id: 0,
    title: '',
    content: '',
    score: 0,
    feedback: ''
  }
  ngOnInit(): void {
    this.loadEssays();
    this.loadEssay(0);
  }

  loadEssays() {
    this.essayService.getAllEssay().subscribe({
      next: (res) => {
        this.essays = res;
        this.cdr.detectChanges()
      },
      error: (err) => {
        console.error("Error", err)
      }
    })
  }
  loadEssay(id: number) {
    this.essayService.getEssayById(id).subscribe({
      next: (res) => {
        this.essay = res;
        this.cdr.detectChanges()
      }, error: (err) => {
        console.error("Error", err)
      }
    })
  }
  saveEssay() {
    this.essayService.createEssay(this.formData).subscribe({
      next: () => {
        this.formData = {
          id: 0,
          title: '',
          content: '',
          score: 0,
          feedback: ''
        };
        this.cdr.detectChanges()
      }, error: (err) => {
        console.error("Error:", err)
      }
    })
  }
  editEssay() {
    this.essayService.updateEssay(this.formData).subscribe({
      next: () => {
        this.formData = {
          id: 0,
          title: '',
          content: '',
          score: 0,
          feedback: ''
        };
        this.cdr.detectChanges()
      }, error: (err) => {
        console.error("Error:", err)
      }
    })
  }
  deleteEssay(id: number) {
    this.essayService.deleteEssay(id).subscribe({
      next: () => {
        this.loadEssays();
        this.cdr.detectChanges()
      }, error: (err) => {
        console.error("Error:", err)
      }
    })
  }
searchText = '';
scoringId: number | null = null;

get filteredEssays(): Essay[] {

  if (!this.searchText.trim()) {
    return this.essays;
  }

  return this.essays.filter(
    essay =>
      essay.title
        .toLowerCase()
        .includes(
          this.searchText.toLowerCase()
        )
  );

}

get averageScore(): number {

  const scoredEssays = this.essays.filter(
    essay => essay.score && essay.score > 0
  );

  if (scoredEssays.length === 0) {
    return 0;
  }

  const total = scoredEssays.reduce(
    (sum, essay) => sum + (essay.score || 0),
    0
  );

  return total / scoredEssays.length;

}

loadEssayToForm(essay: Essay) {

  this.formData = {
    ...essay
  };

}

scoreEssay(id: number) {

  this.scoringId = id;

  this.essayService
    .essayScore(id)
    .subscribe({

      next: () => {

        this.loadEssays();

        this.scoringId = null;

      },

      error: err => {

        console.error(err);

        this.scoringId = null;

      }

    });

}
getScore10(score: number | undefined): number {

  if (!score) {
    return 0;
  }

  return (score / 6) * 10;

}
}
