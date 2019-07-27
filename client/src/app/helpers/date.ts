export function formatDate(dt) {
  if (dt) {
    let date = new Date(dt);
    return (1 + date.getMonth()) + '/' + date.getDate() + '/' + date.getFullYear();
  }
  return '';
}
