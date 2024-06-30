import {other} from "./templates/other";
import {InputComponent} from "./templates/components/form/input.component";
import {SortableList} from "./templates/components/sortable-list/sortable-list";

const main = () => {
    const helloString: string = other('Philgfou');
    console.log(helloString);
    InputComponent('phil!')
    // @ts-ignore
    htmx.onLoad(function (content) {
        SortableList(".sortable");
    })
}


main();
