import { environment } from '@/../environments';
export const config = {
  apiUrl: environment.production ? 'https://cpe202.toshikuboi.net:8080' : 'http://localhost:8080'
};

