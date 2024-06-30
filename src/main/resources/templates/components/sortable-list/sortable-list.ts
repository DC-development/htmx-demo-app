
export function SortableList(selector: string) {
    var sortables = document.querySelectorAll(selector);
    for (var i = 0; i < sortables.length; i++) {
        var sortable = sortables[i];
        // @ts-ignore
        var sortableInstance = new Sortable(sortable, {
            handle: '.handle',
            animation: 150,
            ghostClass: 'sortable-list__item--ghost',
            // Make the `.htmx-indicator` unsortable
            filter: ".htmx-indicator",

            onMove: function (evt) {
                console.log("moving", evt.dragged.id, evt.related.id)
                return evt.related.className.indexOf('htmx-indicator') === -1;
            },
            // Disable sorting on the `end` event
            onEnd: function (evt) {
                console.log("ended", evt.item.id)
                this.option("disabled", false);
            }
        });

        // Re-enable sorting on the `htmx:afterSwap` event
        sortable.addEventListener("htmx:afterSwap", function () {
            console.log('this works')
            sortableInstance.option("disabled", false);
        });
    }

}
