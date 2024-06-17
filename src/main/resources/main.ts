import {other} from "./templates/other";
import {InputComponent} from "./templates/components/form/input.component";

const main = () => {
    const helloString: string = other('Philgggfou!');
    console.log(helloString);
    InputComponent()
}

main();
