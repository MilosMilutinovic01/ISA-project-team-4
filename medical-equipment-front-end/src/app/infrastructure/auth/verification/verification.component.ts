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
  id: number = 0;

  constructor(
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      console.log(this.id);
      this.authService.verify(this.id).subscribe({
        next: () => {
          alert('Successfully verified!');
          this.router.navigate(['']);
        },
        error: (error) => {
          if (error.status === 409) {
            alert('Error!');
          }
        },
      });
    });
  }
}
