import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { IpService } from '../../services/ip-service';
import { OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-ip-card',
  imports: [],
  templateUrl: './ip-card.html',
  styleUrl: './ip-card.css'
})
export class IpCard implements OnInit, OnDestroy {

  publicIp: string = 'Fetching IP...';
  errorMessage: string | null = null;
  private ipSubscription: Subscription | undefined;

  constructor(private userIpService: IpService) { } // Inject the standalone service

  ngOnInit(): void {
    this.fetchIp();
  }

  fetchIp(): void {
    this.publicIp = 'Fetching IP...';
    this.errorMessage = null;

    this.ipSubscription = this.userIpService.getIp().subscribe({ // Call the service method
      next: (data) => {
        this.publicIp = data.ip;
      },
      error: (err) => {
        this.errorMessage = err.message || 'Could not fetch IP address.';
        this.publicIp = 'Error';
      }
    });
  }

  ngOnDestroy(): void {
    if (this.ipSubscription) {
      this.ipSubscription.unsubscribe(); // Prevent memory leaks
    }
  }
  

}
