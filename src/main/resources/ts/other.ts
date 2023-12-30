export const other = (name: string): string => {
    const form: HTMLElement = document.getElementById('sortableListForm')

    form.addEventListener('end', (e) => console.log("drop detected. this is the event: ", e))

    return `huhsu ${name}`
}