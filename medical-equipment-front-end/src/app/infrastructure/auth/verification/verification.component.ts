import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css'],
})
export class VerificationComponent implements OnInit {
  id: String = '';
  error: Boolean = true;

  constructor(
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      this.authService.verify(this.id).subscribe({
        next: () => {
          this.error = false;
          setTimeout(() => {
            this.router.navigate(['']);
          }, 2000);
        },
        error: (error) => {
          this.error = true;
          setTimeout(() => {
            this.router.navigate(['']);
          }, 2000);
        },
      });
    });
  }
}
