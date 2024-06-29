import {other} from "./templates/other";
import {InputComponent} from "./templates/components/form/input.component";

const main = () => {
    const helloString: string = other('Philgfou!');
    console.log(helloString);
    InputComponent('phil')
}

main();
