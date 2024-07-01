import {other} from "./templates/other";
import {InputComponent} from "./templates/components/form/input.component";
import {SortableList} from "./templates/components/sortable-list/sortable-list";

// @ts-ignore
const htmx = window.htmx

htmx.onLoad((content) => {
    console.log('onload triggered', content)
})

export const main = () => {
    const helloString: string = other('Phil!');
    InputComponent(helloString)
    SortableList(".sortable")
}

main();
