import { environment } from '@/../environments';
export const config = {
  apiUrl: environment.production ? 'http://csc365.toshikuboi.net:8080' : 'http://localhost:8080'
};

