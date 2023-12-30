export const other = (name: string): string => {
    const form: HTMLElement = document.getElementById('sortableListForm')
/*
    form.addEventListener('end', (e: any) => {
        console.log("drop detected. this is the event: ", e.item.id.split('-')[1]);
        const droppedElement = document.getElementById(e.item.id)
        droppedElement.setAttribute('class', droppedElement.classList+' dropped')

    })
*/
    return `huhsu ${name}`
}